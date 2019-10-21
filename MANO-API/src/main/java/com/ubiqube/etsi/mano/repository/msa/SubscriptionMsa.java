package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;

/**
 * A single way to handle subscription storage.
 *
 * @author ovi@ubiqube.com
 *
 */
@Service
public class SubscriptionMsa extends AbstractGenericRepository<SubscriptionObject> implements SubscriptionRepository {

	private static final Logger LOG = LoggerFactory.getLogger(SubscriptionMsa.class);

	private static final String NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO";
	private static final String REPOSITORY_SUBSCRIPTION_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/subscriptions";

	public SubscriptionMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter) {
		super(_mapper, _repositoryService, _jsonFilter);
	}

	@Override
	String setId(final SubscriptionObject _entity) {
		final String id = _entity.getSubscriptionsPkgmSubscription().getId();
		if (null == id) {
			_entity.getSubscriptionsPkgmSubscription().setId(UUID.randomUUID().toString());
		}

		return _entity.getSubscriptionsPkgmSubscription().getId();
	}

	@Override
	Class<?> getClazz() {
		return SubscriptionObject.class;
	}

	@Override
	String getRoot() {
		return REPOSITORY_SUBSCRIPTION_BASE_PATH;
	}

	@Override
	String getFilename() {
		return "susbscription.json";
	}

}
