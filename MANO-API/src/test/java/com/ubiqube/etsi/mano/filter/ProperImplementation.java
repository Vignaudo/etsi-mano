package com.ubiqube.etsi.mano.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.grammar.JsonBeanProperty;
import com.ubiqube.etsi.mano.grammar.JsonBeanUtil;

public class ProperImplementation {
	private final JsonBeanUtil jsonBeanUtil;

	public ProperImplementation() {
		jsonBeanUtil = new JsonBeanUtil();
	}

	void testName() throws Exception {
		final List<String> list = new ArrayList<>();
		list.add("_links");
		list.add("userDefinedData.aa.token"); // Doesn't work.
		list.add("checksum.algorithm");
		doFilter(list, new VnfPkgInfo());
	}

	private void doFilter(final List<String> list, final Object object) {
		final Map<String, JsonBeanProperty> objectProperties = jsonBeanUtil.getProperties(object);
		for (final String string : list) {
			// final ViewHolder viewHolder = new ViewHolder(string);
			final JsonBeanProperty jsonBeanProperty = objectProperties.get(string);
			System.out.println(string + "===>" + jsonBeanProperty);

		}
		final Set<String> keys = objectProperties.keySet();
		for (final String string : keys) {
			System.out.println("+ " + string);
		}
	}
}