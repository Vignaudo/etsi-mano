package com.ubiqube.etsi.mano.repository.es;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

public class VnfPackageEs implements VnfPackageRepository {

	@Override
	public VnfPkgInfo get(final String id) {
		// TODO Auto-generated method stub
		return new VnfPkgInfo();
	}

	@Override
	public void delete(final String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public VnfPkgInfo save(final VnfPkgInfo entity) {
		// TODO Auto-generated method stub
		return new VnfPkgInfo();

	}

	@Override
	public List<VnfPkgInfo> query(final String filter) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public byte[] getBinary(final String _id, final String _filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBinary(final String _id, final String _filename, final int min, final Integer max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfLcmOpOcc createLcmOpOccs(final String vnfInstanceId, final LcmOperationType terminate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(final VnfLcmOpOcc lcmOpOccs, final LcmOperationStateType processing) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attachProcessIdToLcmOpOccs(@NotNull final String id, final String processId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeObject(@NotNull final String _id, @NotNull final String _filename, final Object _object) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T, U extends Class> T loadObject(@NotNull final String _id, @NotNull final String _filename, final U t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeBinary(@NotNull final String _id, @NotNull final String _filename, final InputStream _stream) {
		// TODO Auto-generated method stub

	}

}
