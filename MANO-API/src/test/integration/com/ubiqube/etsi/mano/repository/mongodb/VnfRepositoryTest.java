package com.ubiqube.etsi.mano.repository.mongodb;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.Application;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.mongo.VnfPackageMongo;

@RunWith(SpringRunner.class)
@Import(Application.class)
public class VnfRepositoryTest {
	@Autowired
	private VnfPackageMongo vnfPackageMongo;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testName() throws Exception {
		final File src = new File("src/test/integration/vnf-pkg-info.json");
		VnfPkgInfo entity = objectMapper.readValue(src, VnfPkgInfo.class);
		final String id = UUID.randomUUID().toString();
		entity.setId(id);
		vnfPackageMongo.save(entity);

		entity = vnfPackageMongo.get(id);
		assertNotNull(entity);

		vnfPackageMongo.delete(id);

		entity = vnfPackageMongo.get(id);
		assertNull(entity);
	}
}
