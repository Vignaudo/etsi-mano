package com.ubiqube.etsi.mano.repository.es;

import java.io.InputStream;
import java.util.List;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

public class VnfPackageEs implements VnfPackageRepository {

	@Override
	public VnfPkgInfo get(final String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(final String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public VnfPkgInfo save(final VnfPkgInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeObject(final String _vnfPkgId, final InputStream _stream, final String _filename) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeObject(final String _vnfPkgId, final Object _object, final String _filename) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<VnfPkgInfo> query(final String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeBinary(final String _id, final InputStream _stream, final String _filename) {
		// TODO Auto-generated method stub

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

}
