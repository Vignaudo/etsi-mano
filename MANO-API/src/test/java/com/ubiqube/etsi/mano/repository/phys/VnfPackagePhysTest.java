package com.ubiqube.etsi.mano.repository.phys;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileSystemUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.JsonBeanUtil;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public class VnfPackagePhysTest {

	private static final String ROOT = "/tmp/test";
	private final VnfPackagePhys vnfPackagePhys;

	public VnfPackagePhysTest() {
		final JsonFilter jsonFilter = new JsonFilter(new JsonBeanUtil());
		vnfPackagePhys = new VnfPackagePhys(ROOT, new ObjectMapper(), jsonFilter);
	}

	@BeforeEach
	public void beforeEach() {
		FileSystemUtils.deleteRecursively(new File(ROOT));
	}

	@Test
	void testName() throws Exception {

		VnfPkgInfo entity = new VnfPkgInfo();
		vnfPackagePhys.save(entity);
		assertNotNull(entity.getId());

		entity = vnfPackagePhys.get(entity.getId());
		assertNotNull(entity);

		List<VnfPkgInfo> res = vnfPackagePhys.query(null);
		assertNotNull(res);
		assertEquals(1, res.size());

		vnfPackagePhys.delete(entity.getId());

		res = vnfPackagePhys.query(null);
		assertNotNull(res);
		assertEquals(0, res.size());
	}

	@Test
	void testNotFound() throws Exception {
		assertThrows(NotFoundException.class, () -> {
			vnfPackagePhys.get("DEADBEEF");
		});
	}
}
