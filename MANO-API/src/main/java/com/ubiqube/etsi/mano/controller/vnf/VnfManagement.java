package com.ubiqube.etsi.mano.controller.vnf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgIdGetResponse;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.utils.MimeType;
import com.ubiqube.etsi.mano.utils.RangeHeader;
import com.ubiqube.etsi.mano.utils.RangeHeader.FromToBean;

/**
 * This implementation cover VNFO + NFVM & VNFO only.
 *
 * @author ovi@ubiqube.com
 *
 */
@Profile({ "default", "NFVO" })
@Service
public class VnfManagement implements VnfPackageManagement {
	private static final String APPLICATION_ZIP = "application/zip";
	private static final Logger LOG = LoggerFactory.getLogger(VnfManagement.class);

	private final VnfPackageRepository vnfPackageRepository;

	public VnfManagement(final VnfPackageRepository _vnfPackageRepository) {
		super();
		LOG.info("Starting VNF Package Management For NFVO+VNFM or NFVO Only Management.");
		vnfPackageRepository = _vnfPackageRepository;
	}

	@Override
	public VnfPkgInfo vnfPackagesVnfPkgIdGet(final String vnfPkgId, final Linkable links) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgId));
		final VnfPackagesVnfPkgIdGetResponse vnfPackagesVnfPkgIdGetResponse = new VnfPackagesVnfPkgIdGetResponse();
		vnfPackagesVnfPkgIdGetResponse.setVnfPkgInfo(vnfPkgInfo);
		return vnfPkgInfo;
	}

	@Override
	public String vnfPackagesGet(final Map<String, String> queryParameters, final Linkable links) {
		final String filter = queryParameters.get("filter");

		final List<VnfPkgInfo> vnfPkginfos = vnfPackageRepository.query(filter);
		vnfPkginfos.stream().forEach(x -> x.setLinks(links.getVnfLinks(x.getId())));

		final String exclude = queryParameters.get("exclude_fields");
		final String fields = queryParameters.get("fields");

		final ObjectMapper mapperForQuery = MapperForView.getMapperForView(exclude, fields, null, null);

		try {
			return mapperForQuery.writeValueAsString(vnfPkginfos);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	/**
	 * We should not reply a Response here.
	 *
	 * @param vnfPkgId
	 * @param artifactPath
	 * @param _accept
	 * @param rangeHeader
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final String vnfPkgId, final String artifactPath, final RangeHeader rangeHeader) {
		final byte[] content = vnfPackageRepository.getBinary(vnfPkgId, "vnfd");

		final InputStream bis = new ByteArrayInputStream(content);
		final ZipInputStream zis = new ZipInputStream(bis);
		ZipEntry entry = null;
		try {
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				if (entry.getName().equals(artifactPath)) {
					final byte[] zcontent = StreamUtils.copyToByteArray(zis);
					if (rangeHeader != null) {
						final FromToBean ft = rangeHeader.getValues(zcontent.length);
						final byte[] finalContent = Arrays.copyOfRange(zcontent, ft.from, ft.to);
						final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(finalContent));
						final MediaType contentType = MediaType.APPLICATION_OCTET_STREAM;
						return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
								.header("Content-Range", rangeHeader.getContentRange(zcontent.length))
								.contentType(contentType)
								.body(resource);
					}
					final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(zcontent));
					final MediaType contentType = MediaType.APPLICATION_OCTET_STREAM;
					return ResponseEntity.ok()
							.contentType(contentType)
							.body(resource);
				}
			}
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		throw new NotFoundException(new StringBuilder("VNF package artifact not found for vnfPack with id: ")
				.append(vnfPkgId).append(" artifactPath: ").append(artifactPath).toString());
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final String vnfPkgId, final String accept) {
		vnfPackageRepository.get(vnfPkgId);

		// - Implement VNFD multi-files support
		final byte[] content = vnfPackageRepository.getBinary(vnfPkgId, "vnfd");
		final String mime = MimeType.findMatch(content);
		if (MediaType.APPLICATION_JSON_VALUE.contentEquals(mime)) {
			final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(content));
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(resource);
		} else if (APPLICATION_ZIP.equals(mime)) {
			final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(content));
			return ResponseEntity.ok()
					.header("Content-Type", mime)
					.body(resource);
		} else {
			final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(content));
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(resource);
		}
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdPackageContentGet(final String _vnfPkgId, final String _range) {
		final RangeHeader rangeHeader = RangeHeader.fromValue(_range);

		if (rangeHeader != null) {
			final byte[] bytes = vnfPackageRepository.getBinary(_vnfPkgId, "vnfd", rangeHeader.getFrom(), rangeHeader.getTo());
			final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
			final String mime = MimeType.findMatch(bytes);
			return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
					.header("Content-Range", rangeHeader.getContentRange(bytes.length))
					.header("Content-Type", mime)
					.body(resource);
		}
		final byte[] bytes = vnfPackageRepository.getBinary(_vnfPkgId, "vnfd");
		final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
		final String mime = MimeType.findMatch(bytes);
		return ResponseEntity.status(HttpStatus.OK)
				.header("Content-Type", mime)
				.body(resource);
	}

}