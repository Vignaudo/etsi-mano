package com.ubiqube.etsi.mano.repository.phys;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileSystemUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.JsonBeanUtil;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.service.PropertiesConfiguration;

public class VnfPackagePhysTest {

	private static final String ROOT = "/tmp/test";
	private final VnfPackagePhys vnfPackagePhys;
	private final PropertiesConfiguration configuration;

	public VnfPackagePhysTest() {
		final JsonFilter jsonFilter = new JsonFilter(new JsonBeanUtil());
		configuration = new PropertiesConfiguration();
		configuration.set("repository.phys.root", ROOT);
		vnfPackagePhys = new VnfPackagePhys(configuration, new ObjectMapper(), jsonFilter, new LowPhys());
	}

	@BeforeEach
	public void beforeEach() {
		FileSystemUtils.deleteRecursively(new File(ROOT));
		new File(ROOT).mkdirs();
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

	@Test
	void testBadStoreBinary() throws Exception {
		assertThrows(NotFoundException.class, () -> {
			vnfPackagePhys.storeBinary("DEADBEEF", "", "");
		});
	}

	@Test
	void testBadStoreBinary2() throws Exception {
		final FileInputStream fis = new FileInputStream("src/test/resources/VnfPkgInfoModifications.json");
		assertThrows(NotFoundException.class, () -> {
			vnfPackagePhys.storeBinary("DEADBEEF", fis, "");
		});
	}

	@Test
	void testBadGetBinary() throws Exception {
		assertThrows(NotFoundException.class, () -> {
			vnfPackagePhys.getBinary("DEADBEEF", "B16B00B5");
		});
	}

	@Test
	void testBadGetBinary2() throws Exception {
		assertThrows(NotFoundException.class, () -> {
			vnfPackagePhys.getBinary("DEADBEEF", "B16B00B5", 0, 12);
		});
	}

	@Test
	void testGetBinary() throws Exception {
		final VnfPkgInfo entity = new VnfPkgInfo();
		vnfPackagePhys.save(entity);

		vnfPackagePhys.storeBinary(entity.getId(), "{}", "file.txt");

		final byte[] res = vnfPackagePhys.getBinary(entity.getId(), "file.txt");
		assertNotNull(res);
		assertEquals(2, res.length);
		assertEquals('{', res[0]);
		assertEquals('}', res[1]);
	}

	@Test
	void testGetBinaryRange() throws Exception {
		final VnfPkgInfo entity = new VnfPkgInfo();
		vnfPackagePhys.save(entity);

		vnfPackagePhys.storeBinary(entity.getId(), "file.txt", "{}");

		final byte[] res = vnfPackagePhys.getBinary(entity.getId(), "file.txt", 0, 1);
		assertNotNull(res);
		assertEquals(1, res.length);
		assertEquals('{', res[0]);
	}

	@Test
	void testStoreBinary() throws Exception {
		final ByteArrayInputStream bis = new ByteArrayInputStream("{}".getBytes());
		final VnfPkgInfo entity = new VnfPkgInfo();
		vnfPackagePhys.save(entity);

		vnfPackagePhys.storeBinary(entity.getId(), "file.txt", bis);

		final byte[] res = vnfPackagePhys.getBinary(entity.getId(), "file.txt");
		assertNotNull(res);
		assertEquals(2, res.length);
		assertEquals('{', res[0]);
		assertEquals('}', res[1]);
	}

}
