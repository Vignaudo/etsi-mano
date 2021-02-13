/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.ubiqube.etsi.mano.vnfm.v261.controller.nslcm;

import static com.ubiqube.etsi.mano.Constants.getSingleField;
import static com.ubiqube.etsi.mano.vnfm.v261.controller.nslcm.VnfLcmConstants.VNFLCM_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.vnfm.v261.controller.nslcm.VnfLcmConstants.VNFLCM_SEARCH_MANDATORY_FIELDS;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.controller.lcm.LcmLinkable;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstanceLinks;
import com.ubiqube.etsi.mano.controller.lcmgrant.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.CreateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.TerminateVnfRequest;

import ma.glasnost.orika.MapperFacade;

@RolesAllowed({ "ROLE_EM" })
@RestController
public class VnfLcmSol002Api implements VnfLcmSol002 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfLcmSol002Api.class);

	@Nonnull
	private final LcmLinkable links = new Sol002LcmLinkable();

	private final VnfInstanceService vnfInstancesService;

	private final VnfInstanceLcm vnfInstanceLcm;

	private final MapperFacade mapper;

	private final ManoSearchResponseService searchService;

	public VnfLcmSol002Api(final VnfInstanceService _vnfInstancesRepository, final VnfInstanceLcm _vnfInstanceLcm, final MapperFacade _mapper, final ManoSearchResponseService _searchService) {
		vnfInstancesService = _vnfInstancesRepository;
		vnfInstanceLcm = _vnfInstanceLcm;
		mapper = _mapper;
		searchService = _searchService;
		LOG.info("Starting Ns Instance SOL002 Controller.");
	}

	@Override
	public ResponseEntity<String> vnfInstancesGet(final MultiValueMap<String, String> requestParams) {
		final String filter = getSingleField(requestParams, "filter");
		final List<VnfInstance> result = vnfInstanceLcm.get(requestParams);
		final Consumer<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance> setLink = x -> x.setLinks(links.getLinks(x.getId()));
		return searchService.search(requestParams, com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class, VNFLCM_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNFLCM_SEARCH_MANDATORY_FIELDS, result, com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class, setLink);
	}

	@Override
	public ResponseEntity<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance> vnfInstancesPost(final CreateVnfRequest createVnfRequest) {
		final VnfInstance vnfInstance = vnfInstanceLcm.post(createVnfRequest.getVnfdId(), createVnfRequest.getVnfInstanceName(), createVnfRequest.getVnfInstanceDescription());
		final com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance inst = mapper.map(vnfInstance, com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class);
		final VnfInstanceLinks location = links.getLinks(vnfInstance.getId().toString());
		inst.setLinks(location);
		return ResponseEntity.created(URI.create(location.getSelf().getHref())).body(inst);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeExtConnPost(final String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeFlavourPost(final String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdDelete(final String vnfInstanceId) {
		vnfInstanceLcm.delete(UUID.fromString(vnfInstanceId));
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance> vnfInstancesVnfInstanceIdGet(final String vnfInstanceId) {
		final VnfInstance vnfInstanceDb = vnfInstancesService.findById(UUID.fromString(vnfInstanceId));
		final com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance vnfInstance = mapper.map(vnfInstanceDb, com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class);
		vnfInstance.setLinks(links.getLinks(vnfInstanceId));
		return new ResponseEntity<>(vnfInstance, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdHealPost(final String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdInstantiatePost(final String vnfInstanceId, final InstantiateVnfRequest instantiateVnfRequest) {
		final VnfInstantiate req = mapper.map(instantiateVnfRequest, VnfInstantiate.class);
		final VnfBlueprint lcm = vnfInstanceLcm.instantiate(UUID.fromString(vnfInstanceId), req);
		final VnfInstanceLinks link = links.getLinks(lcm.getId().toString());
		return ResponseEntity.accepted().header("Location", link.getSelf().getHref()).build();
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdOperatePost(final String vnfInstanceId) {

		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdPatch(final String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScalePost(final String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScaleToLevelPost(final String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdTerminatePost(final String vnfInstanceId, final TerminateVnfRequest terminateVnfRequest) {
		final VnfBlueprint lcm = vnfInstanceLcm.terminate(UUID.fromString(vnfInstanceId), CancelModeTypeEnum.fromValue(terminateVnfRequest.toString()), terminateVnfRequest.getGracefulTerminationTimeout());
		final VnfInstanceLinks link = links.getLinks(lcm.getId().toString());
		return ResponseEntity.noContent().header("Location", link.getSelf().getHref()).build();
	}
}