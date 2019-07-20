package com.ubiqube.etsi.mano.repository.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

@Service
public class VnfPackageMongo implements VnfPackageRepository {
	@Autowired
	private final MongoTemplate mongoTemplate;

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

}
