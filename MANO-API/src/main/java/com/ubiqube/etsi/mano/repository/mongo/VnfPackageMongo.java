package com.ubiqube.etsi.mano.repository.mongo;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

@Service
public class VnfPackageMongo implements VnfPackageRepository {
	@Autowired
	private final MongoTemplate mongoTemplate;
	private final MongoQueryer queryier = new MongoQueryer();

	@Autowired
	public VnfPackageMongo(final MongoTemplate _mongoTemplate) {
		mongoTemplate = _mongoTemplate;
	}

	@Override
	public VnfPkgInfo get(final String id) {
		final Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, VnfPkgInfo.class);
	}

	@Override
	public void delete(final String id) {
		final Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		mongoTemplate.remove(query, VnfPkgInfo.class);
	}

	@Override
	public VnfPkgInfo save(final VnfPkgInfo entity) {
		return mongoTemplate.save(entity);
	}

	public List<VnfPkgInfo> query(final List<Node> nodes) {
		final Query query = queryier.getCriteria(nodes);
		return mongoTemplate.find(query, VnfPkgInfo.class);
	}

	@Override
	public void storeBinary(final String _vnfPkgId, final InputStream _stream, final String _filename) {
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
