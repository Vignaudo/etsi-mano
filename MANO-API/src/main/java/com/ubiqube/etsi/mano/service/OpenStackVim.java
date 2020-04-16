package com.ubiqube.etsi.mano.service;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.jclouds.ContextBuilder;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.openstack.heat.v1.HeatApi;
import org.jclouds.openstack.heat.v1.domain.Stack;
import org.jclouds.openstack.heat.v1.domain.StackStatus;
import org.jclouds.openstack.heat.v1.features.StackApi;
import org.jclouds.openstack.heat.v1.options.CreateStack;
import org.jclouds.openstack.keystone.config.KeystoneProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

public class OpenStackVim implements Vim {

	private static final Logger LOG = LoggerFactory.getLogger(OpenStackVim.class);

	@Override
	public String onVnfInstanceTerminate(final Map<String, Object> userData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onVnfInstantiate(final String vnfPkgId, final Map<String, Object> userData) {
		final StackApi stackApi = getStackApi();
		final CreateStack options = CreateStack.builder()
				.name("ovi-test")
				.templateUrl("http://10.31.1.247/tech_report/vnf_packages/heat/164bd11f-f792-4543-928d-e8565868d60a/vnfd.yaml")
				.build();
		final Stack stack = stackApi.create(options);
		return stack.getId();
	}

	@Override
	public String onNsInstantiate(final String nsdId, final Map<String, Object> userData) {
		final StackApi stackApi = getStackApi();
		final CreateStack options = CreateStack.builder()
				.name("ovi-test")
				.templateUrl("http://10.31.1.247/tech_report/vnf_packages/heat/164bd11f-f792-4543-928d-e8565868d60a/vnfd.yaml")
				.build();
		final Stack stack = stackApi.create(options);
		return stack.getId();
	}

	@Override
	public String onNsInstanceTerminate(String nsdId, final Map<String, Object> userData) {
		final StackApi stackApi = getStackApi();
		stackApi.delete("", nsdId);
		return "nsdId";
	}

	@Override
	public LcmOperationStateType waitForCompletion(final String processId, final int seconds) {
		final StackApi stackApi = getStackApi();
		final long maxTime = new Date().getTime() + seconds;
		while (new Date().getTime() < maxTime) {
			final Stack st = stackApi.get(processId);
			final StackStatus status = st.getStatus();
			if (convert(status) == LcmOperationStateType.PROCESSING) {
				try {
					Thread.sleep(5 * 1000);
				} catch (final InterruptedException e) {
					LOG.warn("Interrupted.", e);
				}
				continue;
			}
			return convert(status);
		}
		return null;
	}

	private static LcmOperationStateType convert(final StackStatus status) {
		switch (status) {
		case CREATE_COMPLETE:
		case DELETE_COMPLETE:
		case RESUME_COMPLETE:
		case ROLLBACK_COMPLETE:
		case SUSPEND_COMPLETE:
		case UPDATE_COMPLETE:
			return LcmOperationStateType.COMPLETED;
		case CREATE_IN_PROGRESS:
		case DELETE_IN_PROGRESS:
		case RESUME_IN_PROGRESS:
		case ROLLBACK_IN_PROGRESS:
		case SUSPEND_IN_PROGRESS:
		case UPDATE_IN_PROGRESS:
			return LcmOperationStateType.PROCESSING;
		case CREATE_FAILED:
		case DELETE_FAILED:
		case RESUME_FAILED:
		case ROLLBACK_FAILED:
		case SUSPEND_FAILED:
		case UPDATE_FAILED:
		case UNRECOGNIZED: // UNRECONIZED as a failure ?
		default:
			return LcmOperationStateType.FAILED;
		}

	}

	private static StackApi getStackApi() {
		final Properties overrides = new Properties();
		overrides.put(KeystoneProperties.TENANT_NAME, "Default");
		overrides.put(KeystoneProperties.KEYSTONE_VERSION, "3");
		overrides.put(KeystoneProperties.SCOPE, "projectId:ab243747b5d64f70b662e065f5b28300");

		final HeatApi heat = ContextBuilder.newBuilder("openstack-heat")
				.endpoint("http://10.31.1.248:5000/v3/")
				.credentials("admin", "openstack")
				.overrides(overrides)
				.modules(ImmutableSet.of(new SLF4JLoggingModule()))
				.buildApi(HeatApi.class);

		return heat.getStackApi("RegionOne");
	}

}
