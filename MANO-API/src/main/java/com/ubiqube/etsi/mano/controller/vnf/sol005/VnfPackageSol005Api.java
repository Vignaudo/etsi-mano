package com.ubiqube.etsi.mano.controller.vnf.sol005;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.device.DeviceService;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.controller.vnf.VnfManagement;
import com.ubiqube.etsi.mano.exception.BadRequestException;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagePostQuery;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgIdGetResponse;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgIdPackageContentUploadFromUriPostRequest;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.ManufacturerModel;
import com.ubiqube.etsi.mano.service.Patcher;
import com.ubiqube.etsi.mano.utils.RangeHeader;

/**
 * SOL005 - VNF Package Management Interface
 *
 * <p>
 * SOL005 - VNF Package Management Interface IMPORTANT: Please note that this
 * file might be not aligned to the current version of the ETSI Group
 * Specification it refers to and has not been approved by the ETSI NFV ISG. In
 * case of discrepancies the published ETSI Group Specification takes
 * precedence. Please report bugs to
 * https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 * NOTE: Normaly we should receive object in methods but Genson seems to be on
 * the classpath and is unable to unserialize objects. So we use a string2Object
 * to do So. Note same problems occurred when returning object some times genson
 * could be here and not Jackson, in this case you can use object2String.
 *
 */
@RestController
public class VnfPackageSol005Api implements VnfPackageSol005 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageSol005Api.class);
	private static final String REPOSITORY_NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_packages";
	private static final String NCROOT = "ncroot";

	private static final String SOL005 = "SOL005";
	@Nonnull
	private final Linkable links = new Sol005Linkable();
	private final VnfManagement vnfManagement;
	private final ManufacturerModel manufacturerModel;
	private final DeviceService deviceService;
	private final RepositoryService repositoryService;
	private final VnfPackageRepository vnfPackageRepository;
	private final Patcher patcher;

	public VnfPackageSol005Api(VnfManagement _vnfManagement, Patcher _patcher, VnfPackageRepository _vnfPackageRepository, RepositoryService _repositoryService, ManufacturerModel _manufacturerModel, DeviceService _deviceService) {
		vnfManagement = _vnfManagement;
		manufacturerModel = _manufacturerModel;
		deviceService = _deviceService;
		patcher = _patcher;
		vnfPackageRepository = _vnfPackageRepository;
		repositoryService = _repositoryService;
	}

	public ResponseEntity<List<SubscriptionsPkgmSubscription>> subscriptionsGet2(@RequestParam Map<String, String> params) {
		LOG.info("qp => {}", params);
		return new ResponseEntity<List<SubscriptionsPkgmSubscription>>(new ArrayList<>(), HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<String> vnfPackagesGet(Map<String, String> requestParams) throws ServiceException {
		final String resp = vnfManagement.vnfPackagesGet(requestParams, links);
		return ResponseEntity.ok(resp);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(String vnfPkgId, String artifactPath, String accept, String range) throws ServiceException {
		return vnfManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(vnfPkgId, artifactPath, RangeHeader.fromValue(range));
	}

	@Override
	public ResponseEntity<VnfPackagesVnfPkgIdGetResponse> vnfPackagesVnfPkgIdGet(String vnfPkgId, String accept) {
		final VnfPkgInfo vnfPkgInfo = vnfManagement.vnfPackagesVnfPkgIdGet(vnfPkgId, links);
		final VnfPackagesVnfPkgIdGetResponse resp = new VnfPackagesVnfPkgIdGetResponse();
		resp.setVnfPkgInfo(vnfPkgInfo);
		return ResponseEntity.ok(resp);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdPackageContentGet(String vnfPkgId, String accept, String range) {
		return vnfManagement.vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId, range);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(String vnfPkgId, String accept) {
		return vnfManagement.vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, accept);
	}

	/**
	 * Create a new individual VNF package resource. 9.5.2.4
	 *
	 * The POST method creates a new individual VNF package resource.
	 *
	 */
	@Override
	public ResponseEntity<VnfPackagesVnfPkgIdGetResponse> vnfPackagesPost(String accept, String contentType, VnfPackagePostQuery vnfPackagePostQuery) {
		final String vnfPkgId = UUID.randomUUID().toString();
		final Object userDataObject = vnfPackagePostQuery.getCreateVnfPkgInfoRequest().getUserDefinedData();
		final VnfPkgInfo vnfPkgInfo = VnfPackageFactory.createVnfPkgInfo(vnfPkgId, userDataObject);
		vnfPackageRepository.storeObject(vnfPkgId, userDataObject, "Metadata.yaml");

		final VnfPackagesVnfPkgIdGetResponse vnfPackagesVnfPkgIdGetResponse = new VnfPackagesVnfPkgIdGetResponse();
		vnfPackagesVnfPkgIdGetResponse.setVnfPkgInfo(vnfPkgInfo);

		final Map<String, Object> userData = (Map<String, Object>) vnfPkgInfo.getUserDefinedData();

		checkUserData(userData);

		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgId));
		final Object heatDoc = userData.get("heat");
		if (null != heatDoc) {
			vnfPackageRepository.storeObject(vnfPkgId, heatDoc, "vnfd.json");
			vnfPkgInfo.setOnboardingState(OnboardingStateEnum.ONBOARDED);
		}
		vnfPackageRepository.save(vnfPkgInfo);
		return new ResponseEntity<>(vnfPackagesVnfPkgIdGetResponse, HttpStatus.CREATED);
	}

	private void checkUserData(Map<String, Object> userData) {
		final String vimId = (String) userData.get("vimId");
		if (null == vimId) {
			throw new BadRequestException("vimId could not be null");
		}
		try {
			deviceService.getDeviceId(vimId);
		} catch (final ServiceException e) {
			throw new BadRequestException("vimId is not found in MSA.", e);
		}
		final String manufacturerId = (String) userData.get("manufacturerId");
		assert (null != manufacturerId);
		final String modelId = (String) userData.get("modelId");
		assert (null != modelId);
		// Probably not the best place to do that.
		final String manufacturer = manufacturerModel.getManufacturerById(manufacturerId);
		final String model = manufacturerModel.getModelById(manufacturerId, modelId);
		userData.put("manufacturer", manufacturer);
		userData.put("model", model);
	}

	/**
	 * Delete an individual VNF package.
	 *
	 * The DELETE method deletes an individual VNF Package resource.
	 *
	 */
	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdDelete(String vnfPkgId) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		if (!"DISABLED".equals(vnfPkgInfo.getOperationalState())) {
			throw new ConflictException("Packaged is enabled.");
		}
		vnfPackageRepository.delete(vnfPkgId);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Update information about an individual VNF package.
	 *
	 * \&quot;The PATCH method updates the information of a VNF package.\&quot;
	 * \&quot;This method shall follow the provisions specified in the Tables
	 * 9.4.3.3.4-1 and 9.4.3.3.4-2 for URI query parameters, request and response
	 * data structures, and response codes.\&quot;
	 *
	 */
	@Override
	public ResponseEntity<VnfPackagesVnfPkgIdGetResponse> vnfPackagesVnfPkgIdPatch(String vnfPkgId, String body, String contentType) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		if ("DISABLED".equals(vnfPkgInfo.getOperationalState())) {
			throw new ConflictException("Could not patch a disabled VNF Package.");
		}
		patcher.patch(body, vnfPkgInfo);
		vnfPackageRepository.save(vnfPkgInfo);

		final VnfPackagesVnfPkgIdGetResponse vnfPackagesVnfPkgIdGetResponse = new VnfPackagesVnfPkgIdGetResponse();
		vnfPackagesVnfPkgIdGetResponse.setVnfPkgInfo(vnfPkgInfo);
		return new ResponseEntity<>(vnfPackagesVnfPkgIdGetResponse, HttpStatus.OK);
	}

	/**
	 * Upload a VNF package by providing the content of the VNF package.
	 *
	 * The PUT method uploads the content of a VNF package. This method shall follow
	 * the provisions specified in the Tables 9.4.5.3.3-1 and 9.4.5.3.3-2 for URI
	 * query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdPackageContentPut(String vnfPkgId, String accept, MultipartFile file) {
		final String uri = new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(vnfPkgId).toString();
		try {
			if (!repositoryService.exists(uri)) {
				throw new NotFoundException("No such object: " + vnfPkgId);
			}
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}

		if (isZip(file.getContentType())) {
			// Normally we should do an asynchrone call
			try {
				unzip(vnfPkgId, file.getInputStream());
			} catch (final ServiceException | IOException e) {
				throw new GenericException(e);
			}
		}
		return ResponseEntity.noContent().build();
	}

	/**
	 * Upload a VNF package by providing the address information of the VNF package.
	 *
	 * The POST method provides the information for the NFVO to get the content of a
	 * VNF package. This method shall follow the provisions specified in the Tables
	 * 9.4.6.3.1-1 and 9.4.6.3.1-2 for URI query parameters, request and response
	 * data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(String accept, String contentType, String vnfPkgId, VnfPackagesVnfPkgIdPackageContentUploadFromUriPostRequest vnfPackagesVnfPkgIdPackageContentUploadFromUriPostRequest) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		if (!"CREATED".equals(vnfPkgInfo.getOnboardingState())) {
			throw new ConflictException("Onboarding state is not correct.");
		}

		final LinkedHashMap<String, String> uddList = (LinkedHashMap<String, String>) vnfPackagesVnfPkgIdPackageContentUploadFromUriPostRequest.getUploadVnfPkgFromUriRequest().getUserDefinedData();
		final String uri = uddList.get("url");
		final InputStream content = getUrlContent(uri);
		try {
			unzip(vnfPkgId, content);
		} catch (final ServiceException | IOException e) {
			throw new GenericException(e);
		}

		return ResponseEntity.noContent().build();
	}

	private static InputStream getUrlContent(String uri) {
		URL url;
		try {
			url = new URL(uri);
			return (InputStream) url.getContent();
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private void unzip(String vnfPkgId, InputStream fileDetail) throws IOException, ServiceException {
		final ZipInputStream zis = new ZipInputStream(fileDetail);
		ZipEntry ze;
		while ((ze = zis.getNextEntry()) != null) {
			String fileName = ze.getName();
			fileName = sanitize(fileName);
			if (ze.isDirectory()) {
				// XXX/ Fix Path ending by '/'
				if (fileName.endsWith("/")) {
					fileName = fileName.substring(0, fileName.length() - 1);
				}
				final String uri = new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(vnfPkgId).append("/").append(fileName).toString();
				repositoryService.addDirectory(uri, "", SOL005, NCROOT);
				continue;
			}

			vnfPackageRepository.storeObject(vnfPkgId, zis, fileName);
		}
	}

	/**
	 * Prevent directory traversal.
	 *
	 * @param fileName
	 * @return
	 */
	protected String sanitize(String fileName) {
		return fileName.replaceAll("\\.\\.", "");
	}

	private static boolean isZip(String httpAccept) {
		return ("application/zip".equals(httpAccept) || "application/x-zip-compressed".equals(httpAccept));
	}

}
