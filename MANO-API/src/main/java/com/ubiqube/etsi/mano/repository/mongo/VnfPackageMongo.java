package com.ubiqube.etsi.mano.repository.mongo;

import java.io.InputStream;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

@Profile("mongo-db")
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

	@Override
	public List<VnfPkgInfo> query(final String filter) {
		final Query query = queryier.getCriteria(filter);
		return mongoTemplate.find(query, VnfPkgInfo.class);
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
