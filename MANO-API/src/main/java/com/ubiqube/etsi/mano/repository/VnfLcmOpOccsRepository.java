package com.ubiqube.etsi.mano.repository;

import java.util.List;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

public interface VnfLcmOpOccsRepository extends CrudRepository<VnfLcmOpOccs>, BinaryRepository {

	void save(List<VnfLcmOpOccs> vnfLcmOpOccsIds);

	void updateState(@Nonnull final VnfLcmOpOccs lcmOpOccs, final LcmOperationStateType operationState);

	void attachProcessIdToLcmOpOccs(@Nonnull final String id, final String processId);
}
