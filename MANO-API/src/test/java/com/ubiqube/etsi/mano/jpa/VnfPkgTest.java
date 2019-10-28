package com.ubiqube.etsi.mano.jpa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.metamodel.internal.EntityTypeImpl;
import org.hibernate.metamodel.internal.PluralAttributeImpl;
import org.hibernate.metamodel.internal.SingularAttributeImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ubiqube.etsi.mano.Application;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.repository.jpa.VnfPackageDb;

@SpringBootTest(classes = Application.class)
public class VnfPkgTest {
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private VnfPackageDb vnfPackageFacade;

	void testName() throws Exception {
		final Metamodel mm = em.getMetamodel();
		final EntityType<VnfPackage> vnfMm = mm.entity(VnfPackage.class);
		final Set<Attribute<? super VnfPackage, ?>> set = vnfMm.getAttributes();
		for (final Attribute<? super VnfPackage, ?> attribute : set) {
			System.out.println("" + attribute.getName() + " " + attribute.getClass());
			final ManagedType<? super VnfPackage> dt = attribute.getDeclaringType();
			System.out.println("" + dt.getPersistenceType());
			if (PluralAttributeImpl.class.isInstance(attribute)) {
				final Class<?> jpaName = getJpaName((PluralAttributeImpl) attribute);
				System.out.println("=========" + jpaName);
				mm.entity(jpaName);
			} else if (SingularAttributeImpl.class.isInstance(attribute)) {
				System.out.println("-----------------");
				final Class<?> clazz = getImplClass((SingularAttributeImpl) attribute);
			}
		}
	}

	void testName2() throws Exception {
		final Map<String, From> joins = new HashMap<>();
		final String field = "softwareImages.checksum.algorithm";
		final Metamodel mm = em.getMetamodel();
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<Object> q = builder.createQuery();
		final Root<VnfPackage> root = q.from(VnfPackage.class);
		final Join<Object, Object> jsi = root.join("softwareImages");
		final Join<Object, Object> jc = jsi.join("checksum");

		final Predicate p = builder.equal(jc.get("algorithm"), "SHA-512");
		q.select(root).where(p);
		em.createQuery(q).getResultList();

		final String[] arr = field.split("\\/");
		if (arr.length > 1) {
			final String ro[] = new String[arr.length - 1];
			System.arraycopy(arr, 0, ro, 0, ro.length);
			final String key = Arrays.asList(ro).stream().collect(Collectors.joining("."));
			joins.get(key);
		}
	}

	@Test
	void testQueryNull() throws Exception {
		vnfPackageFacade.query(null);
	}

	private Class<?> getImplClass(final SingularAttributeImpl attribute) {
		System.out.println(">" + attribute.getBindableJavaType());
		return null;
	}

	private Class<?> getJpaName(final PluralAttributeImpl attribute) {
		final EntityTypeImpl<?> et = (EntityTypeImpl<?>) attribute.getElementType();
		return et.getJavaType();
	}
}
