package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;

@Service
public class VnfInstancesMsa extends AbstractGenericRepository<VnfInstance> implements VnfInstancesRepository {
	private static final String REPOSITORY_VNF_INSTANCE_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_instances";

	public VnfInstancesMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter) {
		super(_mapper, _repositoryService, _jsonFilter);
	}

	@Override
	String setId(final VnfInstance _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return _entity.getId();
	}

	@Override
	Class<?> getClazz() {
		return VnfInstance.class;
	}

	@Override
	String getRoot() {
		return REPOSITORY_VNF_INSTANCE_DATAFILE_BASE_PATH;
	}

	@Override
	String getFilename() {
		return "vnfInstance.json";
	}

}