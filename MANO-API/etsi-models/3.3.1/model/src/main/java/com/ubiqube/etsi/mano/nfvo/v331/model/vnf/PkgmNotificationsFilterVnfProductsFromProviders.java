package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * PkgmNotificationsFilterVnfProductsFromProviders
 */
@Validated
public class PkgmNotificationsFilterVnfProductsFromProviders {
	@JsonProperty("vnfProductName")
	private String vnfProductName = null;

	@JsonProperty("versions")
	@Valid
	private List<PkgmNotificationsFilterVersions> versions = null;

	public PkgmNotificationsFilterVnfProductsFromProviders vnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
		return this;
	}

	/**
	 * Name of the VNF product to match.
	 *
	 * @return vnfProductName
	 **/
	@ApiModelProperty(required = true, value = "Name of the VNF product to match. ")
	@NotNull

	public String getVnfProductName() {
		return vnfProductName;
	}

	public void setVnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders versions(final List<PkgmNotificationsFilterVersions> versions) {
		this.versions = versions;
		return this;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders addVersionsItem(final PkgmNotificationsFilterVersions versionsItem) {
		if (this.versions == null) {
			this.versions = new ArrayList<>();
		}
		this.versions.add(versionsItem);
		return this;
	}

	/**
	 * If present, match VNF packages that contain VNF products with certain
	 * versions and a certain product name, from one particular provider.
	 *
	 * @return versions
	 **/
	@ApiModelProperty(value = "If present, match VNF packages that contain VNF products with certain versions and a certain product name, from one particular provider. ")
	@Valid
	public List<PkgmNotificationsFilterVersions> getVersions() {
		return versions;
	}

	public void setVersions(final List<PkgmNotificationsFilterVersions> versions) {
		this.versions = versions;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PkgmNotificationsFilterVnfProductsFromProviders pkgmNotificationsFilterVnfProductsFromProviders = (PkgmNotificationsFilterVnfProductsFromProviders) o;
		return Objects.equals(this.vnfProductName, pkgmNotificationsFilterVnfProductsFromProviders.vnfProductName) &&
				Objects.equals(this.versions, pkgmNotificationsFilterVnfProductsFromProviders.versions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfProductName, versions);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PkgmNotificationsFilterVnfProductsFromProviders {\n");

		sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
		sb.append("    versions: ").append(toIndentedString(versions)).append("\n");
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
