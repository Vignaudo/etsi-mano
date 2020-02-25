package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.VnfInstanceInstantiatedVnfInfo;

@Entity
@Indexed

public class VnfInstance implements BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@Transient
	private VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = null;

	@Enumerated(EnumType.STRING)
	@Field
	private InstantiationStateEnum instantiationState = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> metadata = null;

	// @OneToMany // (fetch = FetchType.EAGER)
	@Transient
	private List<VimConnectionInformation> vimConnectionInfo = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> vnfConfigurableProperties = null;

	@Field
	private String vnfdId = null;

	@Field
	private String vnfdVersion = null;

	@Field
	private String vnfInstanceDescription = null;

	@Field
	private String vnfInstanceName = null;

	@OneToOne(fetch = FetchType.EAGER)
	private VnfPackage vnfPkg = null;

	@Field
	private String vnfProductName = null;

	@Field
	private String vnfProvider = null;

	@Field
	private String vnfSoftwareVersion = null;

	@OneToOne(fetch = FetchType.EAGER)
	private NsdInstance nsInstance;

	private String processId;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> extensions = null;

	@Override
	public UUID getId() {
		return id;
	}

	public VnfInstanceInstantiatedVnfInfo getInstantiatedVnfInfo() {
		return instantiatedVnfInfo;
	}

	public InstantiationStateEnum getInstantiationState() {
		return instantiationState;
	}

	public List<VimConnectionInformation> getVimConnectionInfo() {
		return vimConnectionInfo;
	}

	public String getVnfdId() {
		return vnfdId;
	}

	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public String getVnfInstanceDescription() {
		return vnfInstanceDescription;
	}

	public String getVnfInstanceName() {
		return vnfInstanceName;
	}

	public String getVnfProductName() {
		return vnfProductName;
	}

	public String getVnfProvider() {
		return vnfProvider;
	}

	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public void setInstantiatedVnfInfo(final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
		this.instantiatedVnfInfo = instantiatedVnfInfo;
	}

	public void setInstantiationState(final InstantiationStateEnum instantiationState) {
		this.instantiationState = instantiationState;
	}

	public void setVimConnectionInfo(final List<VimConnectionInformation> vimConnectionInfo) {
		this.vimConnectionInfo = vimConnectionInfo;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public void setVnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	public void setVnfInstanceDescription(final String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
	}

	public void setVnfInstanceName(final String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
	}

	public void setVnfPkg(final VnfPackage vnfPkgId) {
		this.vnfPkg = vnfPkgId;
	}

	public void setVnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	public void setVnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	public void setVnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public Map<String, String> getVnfConfigurableProperties() {
		return vnfConfigurableProperties;
	}

	public VnfPackage getVnfPkg() {
		return vnfPkg;
	}

	public Map<String, String> getExtensions() {
		return extensions;
	}

	public NsdInstance getNsInstance() {
		return nsInstance;
	}

	public void setNsInstance(final NsdInstance nsInstance) {
		this.nsInstance = nsInstance;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public void setVnfConfigurableProperties(final Map<String, String> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
	}

	public void setExtensions(final Map<String, String> extensions) {
		this.extensions = extensions;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(final String processId) {
		this.processId = processId;
	}

}
