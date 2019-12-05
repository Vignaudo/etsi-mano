package com.ubiqube.etsi.mano.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.KeyValuePairs;
import com.ubiqube.etsi.mano.model.VimConnectionInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstanceLinks;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a VNF instance.
 */
@ApiModel(description = "This type represents a VNF instance. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-08T09:30:40.833+02:00")

public class VnfInstance {
	@JsonProperty("id")
	private String id = null;

	// OVI: Must be removed.
	@JsonProperty("vnfPkgId")
	private String vnfPkgId = null;

	@JsonProperty("vnfInstanceName")
	private String vnfInstanceName = null;

	@JsonProperty("vnfInstanceDescription")
	private String vnfInstanceDescription = null;

	@JsonProperty("vnfdId")
	private String vnfdId = null;

	@JsonProperty("vnfProvider")
	private String vnfProvider = null;

	@JsonProperty("vnfProductName")
	private String vnfProductName = null;

	@JsonProperty("vnfSoftwareVersion")
	private String vnfSoftwareVersion = null;

	@JsonProperty("vnfdVersion")
	private String vnfdVersion = null;

	@JsonProperty("vnfConfigurableProperties")
	private KeyValuePairs vnfConfigurableProperties = null;

	@JsonProperty("vimConnectionInfo")
	@Valid
	private List<VimConnectionInfo> vimConnectionInfo = null;

	@JsonProperty("instantiationState")
	private InstantiationStateEnum instantiationState = null;

	@JsonProperty("instantiatedVnfInfo")
	private VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = null;

	@JsonProperty("metadata")
	private KeyValuePairs metadata = null;

	@JsonProperty("extensions")
	private KeyValuePairs extensions = null;

	@JsonProperty("_links")
	private VnfInstanceLinks links = null;

	public VnfInstance id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the VNF instance.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNF instance. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfInstance vnfInstanceName(final String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
		return this;
	}

	public String getVnfPkgId() {
		return vnfPkgId;
	}

	public void setVnfPkgId(final String vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
	}

	/**
	 * Name of the VNF instance. This attribute can be modified with the PATCH
	 * method.
	 *
	 * @return vnfInstanceName
	 **/
	@ApiModelProperty(value = "Name of the VNF instance. This attribute can be modified with the PATCH method. ")

	public String getVnfInstanceName() {
		return vnfInstanceName;
	}

	public void setVnfInstanceName(final String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
	}

	public VnfInstance vnfInstanceDescription(final String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
		return this;
	}

	/**
	 * Human-readable description of the VNF instance. This attribute can be
	 * modified with the PATCH method.
	 *
	 * @return vnfInstanceDescription
	 **/
	@ApiModelProperty(value = "Human-readable description of the VNF instance. This attribute can be modified with the PATCH method. ")

	public String getVnfInstanceDescription() {
		return vnfInstanceDescription;
	}

	public void setVnfInstanceDescription(final String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
	}

	public VnfInstance vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Identifier of the VNFD on which the VNF instance is based.
	 *
	 * @return vnfdId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNFD on which the VNF instance is based. ")
	@NotNull

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public VnfInstance vnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
		return this;
	}

	/**
	 * Provider of the VNF and the VNFD. The value is copied from the VNFD.
	 *
	 * @return vnfProvider
	 **/
	@ApiModelProperty(required = true, value = "Provider of the VNF and the VNFD. The value is copied from the VNFD. ")
	@NotNull

	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	public VnfInstance vnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
		return this;
	}

	/**
	 * Name to identify the VNF Product. The value is copied from the VNFD.
	 *
	 * @return vnfProductName
	 **/
	@ApiModelProperty(required = true, value = "Name to identify the VNF Product. The value is copied from the VNFD. ")
	@NotNull

	public String getVnfProductName() {
		return vnfProductName;
	}

	public void setVnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	public VnfInstance vnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
		return this;
	}

	/**
	 * Software version of the VNF. The value is copied from the VNFD.
	 *
	 * @return vnfSoftwareVersion
	 **/
	@ApiModelProperty(required = true, value = "Software version of the VNF. The value is copied from the VNFD. ")
	@NotNull

	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	public VnfInstance vnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
		return this;
	}

	/**
	 * Identifies the version of the VNFD. The value is copied from the VNFD.
	 *
	 * @return vnfdVersion
	 **/
	@ApiModelProperty(required = true, value = "Identifies the version of the VNFD. The value is copied from the VNFD. ")
	@NotNull

	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public void setVnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	public VnfInstance vnfConfigurableProperties(final KeyValuePairs vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
		return this;
	}

	/**
	 * Current values of the configurable properties of the VNF instance.
	 * Configurable properties referred in this attribute are declared in the VNFD.
	 * ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on
	 * TOSCA specifications. VNF configurable properties are sometimes also referred
	 * to as configuration parameters applicable to a VNF. Some of these are set
	 * prior to instantiation and cannot be modified if the VNF is instantiated,
	 * some are set prior to instantiation (are part of initial configuration) and
	 * can be modified later, and others can be set only after instantiation. The
	 * applicability of certain configuration may depend on the VNF and the required
	 * operation of the VNF at a certain point in time. These configurable
	 * properties include the following standard attributes, which are declared in
	 * the VNFD if auto-scaling and/or auto-healing are supported by the VNF: *
	 * isAutoscaleEnabled: If present, the VNF supports auto-scaling. If set to
	 * true, auto-scaling is currently enabled. If set to false, auto-scaling is
	 * currently disabled. * isAutohealEnabled: If present, the VNF supports
	 * auto-healing. If set to true, auto-healing is currently enabled. If set to
	 * false, auto-healing is currently disabled. This attribute can be modified
	 * with the PATCH method.
	 *
	 * @return vnfConfigurableProperties
	 **/
	@ApiModelProperty(value = "Current values of the configurable properties of the VNF instance. Configurable properties referred in this attribute are declared in the VNFD. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. VNF configurable properties are sometimes also referred to as configuration parameters applicable to a VNF. Some of these are set prior to instantiation and cannot be modified if the VNF is instantiated, some are set prior to instantiation (are part of initial configuration) and can be modified later, and others can be set only after instantiation. The applicability of certain configuration may depend on the VNF and the required operation of the VNF at a certain point in time. These configurable properties include the following standard attributes, which are declared in the VNFD if auto-scaling and/or auto-healing are supported by the VNF: * isAutoscaleEnabled: If present, the VNF supports auto-scaling. If   set to true, auto-scaling is currently enabled. If set to false,   auto-scaling is currently disabled. * isAutohealEnabled: If present, the VNF supports auto-healing. If   set to true, auto-healing is currently enabled. If set to false,   auto-healing is currently disabled. This attribute can be modified with the PATCH method. ")

	@Valid

	public KeyValuePairs getVnfConfigurableProperties() {
		return vnfConfigurableProperties;
	}

	public void setVnfConfigurableProperties(final KeyValuePairs vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
	}

	public VnfInstance vimConnectionInfo(final List<VimConnectionInfo> vimConnectionInfo) {
		this.vimConnectionInfo = vimConnectionInfo;
		return this;
	}

	public VnfInstance addVimConnectionInfoItem(final VimConnectionInfo vimConnectionInfoItem) {
		if (this.vimConnectionInfo == null) {
			this.vimConnectionInfo = new ArrayList<>();
		}
		this.vimConnectionInfo.add(vimConnectionInfoItem);
		return this;
	}

	/**
	 * Information about VIM connections to be used for managing the resources for
	 * the VNF instance. This attribute shall only be supported and present if
	 * VNF-related resource management in direct mode is applicable. This attribute
	 * can be modified with the PATCH method.
	 *
	 * @return vimConnectionInfo
	 **/
	@ApiModelProperty(value = "Information about VIM connections to be used for managing the resources for the VNF instance. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable. This attribute can be modified with the PATCH method. ")

	@Valid

	public List<VimConnectionInfo> getVimConnectionInfo() {
		return vimConnectionInfo;
	}

	public void setVimConnectionInfo(final List<VimConnectionInfo> vimConnectionInfo) {
		this.vimConnectionInfo = vimConnectionInfo;
	}

	public VnfInstance instantiationState(final InstantiationStateEnum instantiationState) {
		this.instantiationState = instantiationState;
		return this;
	}

	/**
	 * The instantiation state of the VNF.
	 *
	 * @return instantiationState
	 **/
	@ApiModelProperty(required = true, value = "The instantiation state of the VNF. ")
	@NotNull

	public InstantiationStateEnum getInstantiationState() {
		return instantiationState;
	}

	public void setInstantiationState(final InstantiationStateEnum instantiationState) {
		this.instantiationState = instantiationState;
	}

	public VnfInstance instantiatedVnfInfo(final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
		this.instantiatedVnfInfo = instantiatedVnfInfo;
		return this;
	}

	/**
	 * Get instantiatedVnfInfo
	 *
	 * @return instantiatedVnfInfo
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public VnfInstanceInstantiatedVnfInfo getInstantiatedVnfInfo() {
		return instantiatedVnfInfo;
	}

	public void setInstantiatedVnfInfo(final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
		this.instantiatedVnfInfo = instantiatedVnfInfo;
	}

	public VnfInstance metadata(final KeyValuePairs metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Additional VNF-specific attributes that provide metadata describing the VNF
	 * instance. These attributes represent values that are stored persistently in
	 * the VnfInstance structure for consumption by functional blocks that invoke
	 * the VNF lifecycle management interface. They are not consumed by the VNFM, or
	 * the lifecycle management scripts. Modifying the values of these attributes
	 * has no effect on the VNF instance, it only affects the information
	 * represented in the VnfInstance structure. Metadata that are writeable are the
	 * VNF provider foresees are expected to be declared in the VNFD. The
	 * declaration of metadata in the VNFD can optionally contain the specification
	 * of initial values. The VNFM shall accept requests to write metadata that are
	 * not declared in the VNFD. These attributes can be initialized with default
	 * values from the VNFD or with values passed in the CreateVnfRequest structure
	 * (see clause 5.5.2.3). This attributeThese attributes can be created, modified
	 * or removed with the PATCH method. ETSI GS NFV-SOL 001 specifies the structure
	 * and format of the VNFD based on TOSCA specifications. Upon creation of the
	 * VnfInstance structure, the VNFM shall create and initialize all child
	 * attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\"
	 * that were declared in the VNFD with a defined initial value. Child attributes
	 * of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that have
	 * no declared initial value shall not be created, in order to be consistent
	 * with the semantics of the JSON Merge Patch method (see IETF RFC 7396) that
	 * interprets null values as deletion request.
	 *
	 * @return metadata
	 **/
	@ApiModelProperty(value = "Additional VNF-specific attributes that provide metadata describing the VNF instance. These attributes represent values that are stored persistently in the VnfInstance structure for consumption by functional blocks that invoke the VNF lifecycle management interface. They are not consumed by the VNFM, or the lifecycle management scripts. Modifying the values of these attributes has no effect on the VNF instance, it only affects the information represented in the VnfInstance structure. Metadata that are writeable are the VNF provider foresees are expected to be declared in the VNFD. The declaration of metadata in the VNFD can optionally contain the specification of initial values. The VNFM shall accept requests to write metadata that are not declared in the VNFD. These attributes can be initialized with default values from the VNFD or with values passed in the CreateVnfRequest structure (see clause 5.5.2.3). This attributeThese attributes can be created, modified or removed with the PATCH method. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. Upon creation of the VnfInstance structure, the VNFM shall create and initialize all child attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that were declared in the VNFD with a defined initial value. Child attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that have no declared initial value shall not be created, in order to be consistent with the semantics of the JSON Merge Patch method (see IETF RFC 7396) that interprets null values as deletion request. ")

	@Valid

	public KeyValuePairs getMetadata() {
		return metadata;
	}

	public void setMetadata(final KeyValuePairs metadata) {
		this.metadata = metadata;
	}

	public VnfInstance extensions(final KeyValuePairs extensions) {
		this.extensions = extensions;
		return this;
	}

	/**
	 * Additional VNF-specific attributes that affect the lifecycle management of
	 * this VNF instance. These attributes represent values that are stored
	 * persistently in the VnfInstance structure for consumption by the VNFM or the
	 * lifecycle management scripts during the execution of VNF lifecycle management
	 * operations. All extensions that are allowed for the VNF are declared in the
	 * VNFD. The declaration of an extension in the VNFD contains information on
	 * whether its presence is optional or required, and optionally can specify an
	 * initial value. See note 2 and note 4. The VNFM shall reject requests to write
	 * extension attributes that are not declared in the VNFD with a \"422
	 * Unprocessable entity\" error response as defined in clause 6.4 of ETSI GS
	 * NFV-SOL 013. Modifying the values of these attributes has no direct effect on
	 * the VNF instance; however, the modified attribute values can be considered
	 * during subsequent VNF lifecycle management operations, which means that the
	 * modified values can indirectly affect the configuration of the VNF instance.
	 * These attributes can be initialized with default values from the VNFD or with
	 * values passed in the InstantiateVnfRequest structure (see clause 5.5.2.4).
	 * Attributes initialized with default values from the VNFD can be updated with
	 * values passed in the InstantiateVnfRequest structure. Further, these
	 * attributes can be created, modified or deleted with the PATCH method. Upon
	 * creation of the VnfInstance structure, the VNFM shall create and initialize
	 * all child attributes of \"vnfConfigurableProperties\", \"metadata\" and
	 * \"extensions\" that were declared in the VNFD with a defined initial value.
	 * Child attributes of \"vnfConfigurableProperties\", \"metadata\" and
	 * \"extensions\" that have no declared initial value shall not be created, in
	 * order to be consistent with the semantics of the JSON Merge Patch method (see
	 * IETF RFC 7396) that interprets null values as deletion request.
	 *
	 * @return extensions
	 **/
	@ApiModelProperty(value = "Additional VNF-specific attributes that affect the lifecycle management of this VNF instance. These attributes represent values that are stored persistently in the VnfInstance structure for consumption by the VNFM or the lifecycle management scripts during the execution of VNF lifecycle management operations. All extensions that are allowed for the VNF are declared in the VNFD. The declaration of an extension in the VNFD contains information on whether its presence is optional or required, and optionally can specify an initial value. See note 2 and note 4. The VNFM shall reject requests to write extension attributes that are not declared in the VNFD with a \"422 Unprocessable entity\" error response as defined in clause 6.4 of ETSI GS NFV-SOL 013. Modifying the values of these attributes has no direct effect on the VNF instance; however, the modified attribute values can be considered during subsequent VNF lifecycle management operations, which means that the modified values can indirectly affect the configuration of the VNF instance. These attributes can be initialized with default values from the VNFD or with values passed in the InstantiateVnfRequest structure (see clause 5.5.2.4). Attributes initialized with default values from the VNFD can be updated with values passed in the InstantiateVnfRequest structure. Further, these attributes can be created, modified or deleted with the PATCH method. Upon creation of the VnfInstance structure, the VNFM shall create and initialize all child attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that were declared in the VNFD with a defined initial value. Child attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that have no declared initial value shall not be created, in order to be consistent with the semantics of the JSON Merge Patch method (see IETF RFC 7396) that interprets null values as deletion request. ")

	@Valid

	public KeyValuePairs getExtensions() {
		return extensions;
	}

	public void setExtensions(final KeyValuePairs extensions) {
		this.extensions = extensions;
	}

	public VnfInstance links(final VnfInstanceLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Get links
	 *
	 * @return links
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public VnfInstanceLinks getLinks() {
		return links;
	}

	public void setLinks(final VnfInstanceLinks links) {
		this.links = links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstance vnfInstance = (VnfInstance) o;
		return Objects.equals(this.id, vnfInstance.id) &&
				Objects.equals(this.vnfInstanceName, vnfInstance.vnfInstanceName) &&
				Objects.equals(this.vnfInstanceDescription, vnfInstance.vnfInstanceDescription) &&
				Objects.equals(this.vnfdId, vnfInstance.vnfdId) &&
				Objects.equals(this.vnfPkgId, vnfInstance.vnfPkgId) &&
				Objects.equals(this.vnfProvider, vnfInstance.vnfProvider) &&
				Objects.equals(this.vnfProductName, vnfInstance.vnfProductName) &&
				Objects.equals(this.vnfSoftwareVersion, vnfInstance.vnfSoftwareVersion) &&
				Objects.equals(this.vnfdVersion, vnfInstance.vnfdVersion) &&
				Objects.equals(this.vnfConfigurableProperties, vnfInstance.vnfConfigurableProperties) &&
				Objects.equals(this.vimConnectionInfo, vnfInstance.vimConnectionInfo) &&
				Objects.equals(this.instantiationState, vnfInstance.instantiationState) &&
				Objects.equals(this.instantiatedVnfInfo, vnfInstance.instantiatedVnfInfo) &&
				Objects.equals(this.metadata, vnfInstance.metadata) &&
				Objects.equals(this.extensions, vnfInstance.extensions) &&
				Objects.equals(this.links, vnfInstance.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vnfInstanceName, vnfInstanceDescription, vnfdId, vnfProvider, vnfPkgId, vnfProductName, vnfSoftwareVersion, vnfdVersion, vnfConfigurableProperties, vimConnectionInfo, instantiationState, instantiatedVnfInfo, metadata, extensions, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstance {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
		sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
		sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
		sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
		sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
		sb.append("    vnfdVersion: ").append(toIndentedString(vnfdVersion)).append("\n");
		sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
		sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
		sb.append("    instantiationState: ").append(toIndentedString(instantiationState)).append("\n");
		sb.append("    instantiatedVnfInfo: ").append(toIndentedString(instantiatedVnfInfo)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
