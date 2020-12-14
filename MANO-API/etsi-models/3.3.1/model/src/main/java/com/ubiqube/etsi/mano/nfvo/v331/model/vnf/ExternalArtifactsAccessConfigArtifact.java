package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Access configuration information for an external artifact.
 */
@ApiModel(description = "Access configuration information for an external artifact. ")
@Validated
public class ExternalArtifactsAccessConfigArtifact {
	@JsonProperty("artifactUri")
	private String artifactUri = null;

	@JsonProperty("overrideUri")
	private String overrideUri = null;

	/**
	 * Defines the type of authentication / authorization for downloading the VNF
	 * package. Permitted values: - BASIC: Only the \"username\" and \"password\"
	 * attributes shall be present. - OAUTH2_CLIENT_CREDENTIAL S: Only the
	 * \"paramsOauth2ClientCredentials\" attribute shall be present. This attribute
	 * shall not be present if no credentials are provided for the artifact.
	 */
	public enum AuthTypeEnum {
		BASIC("BASIC"),

		OAUTH2_CLIENT_CREDENTIALS("OAUTH2_CLIENT_CREDENTIALS");

		private final String value;

		AuthTypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static AuthTypeEnum fromValue(final String text) {
			for (final AuthTypeEnum b : AuthTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("authType")
	private AuthTypeEnum authType = null;

	@JsonProperty("username")
	private String username = null;

	@JsonProperty("password")
	private String password = null;

	@JsonProperty("paramsOauth2ClientCredentials")
	private ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials paramsOauth2ClientCredentials = null;

	public ExternalArtifactsAccessConfigArtifact artifactUri(final String artifactUri) {
		this.artifactUri = artifactUri;
		return this;
	}

	/**
	 * Get artifactUri
	 *
	 * @return artifactUri
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getArtifactUri() {
		return artifactUri;
	}

	public void setArtifactUri(final String artifactUri) {
		this.artifactUri = artifactUri;
	}

	public ExternalArtifactsAccessConfigArtifact overrideUri(final String overrideUri) {
		this.overrideUri = overrideUri;
		return this;
	}

	/**
	 * Get overrideUri
	 *
	 * @return overrideUri
	 **/
	@ApiModelProperty(value = "")

	public String getOverrideUri() {
		return overrideUri;
	}

	public void setOverrideUri(final String overrideUri) {
		this.overrideUri = overrideUri;
	}

	public ExternalArtifactsAccessConfigArtifact authType(final AuthTypeEnum authType) {
		this.authType = authType;
		return this;
	}

	/**
	 * Defines the type of authentication / authorization for downloading the VNF
	 * package. Permitted values: - BASIC: Only the \"username\" and \"password\"
	 * attributes shall be present. - OAUTH2_CLIENT_CREDENTIAL S: Only the
	 * \"paramsOauth2ClientCredentials\" attribute shall be present. This attribute
	 * shall not be present if no credentials are provided for the artifact.
	 *
	 * @return authType
	 **/
	@ApiModelProperty(value = "Defines the type of authentication / authorization for downloading the VNF package. Permitted values: - BASIC: Only the \"username\" and \"password\" attributes shall be present. - OAUTH2_CLIENT_CREDENTIAL S: Only the \"paramsOauth2ClientCredentials\" attribute shall be present. This attribute shall not be present if no credentials are provided for the artifact. ")

	public AuthTypeEnum getAuthType() {
		return authType;
	}

	public void setAuthType(final AuthTypeEnum authType) {
		this.authType = authType;
	}

	public ExternalArtifactsAccessConfigArtifact username(final String username) {
		this.username = username;
		return this;
	}

	/**
	 * User name to be used for authentication.
	 *
	 * @return username
	 **/
	@ApiModelProperty(value = "User name to be used for authentication. ")

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public ExternalArtifactsAccessConfigArtifact password(final String password) {
		this.password = password;
		return this;
	}

	/**
	 * Password to be used for authentication. Shall not be present in response
	 * bodies.
	 *
	 * @return password
	 **/
	@ApiModelProperty(value = "Password to be used for authentication. Shall not be present in response bodies. ")

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public ExternalArtifactsAccessConfigArtifact paramsOauth2ClientCredentials(final ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
		this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
		return this;
	}

	/**
	 * Get paramsOauth2ClientCredentials
	 *
	 * @return paramsOauth2ClientCredentials
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials getParamsOauth2ClientCredentials() {
		return paramsOauth2ClientCredentials;
	}

	public void setParamsOauth2ClientCredentials(final ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
		this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ExternalArtifactsAccessConfigArtifact externalArtifactsAccessConfigArtifact = (ExternalArtifactsAccessConfigArtifact) o;
		return Objects.equals(this.artifactUri, externalArtifactsAccessConfigArtifact.artifactUri) &&
				Objects.equals(this.overrideUri, externalArtifactsAccessConfigArtifact.overrideUri) &&
				Objects.equals(this.authType, externalArtifactsAccessConfigArtifact.authType) &&
				Objects.equals(this.username, externalArtifactsAccessConfigArtifact.username) &&
				Objects.equals(this.password, externalArtifactsAccessConfigArtifact.password) &&
				Objects.equals(this.paramsOauth2ClientCredentials, externalArtifactsAccessConfigArtifact.paramsOauth2ClientCredentials);
	}

	@Override
	public int hashCode() {
		return Objects.hash(artifactUri, overrideUri, authType, username, password, paramsOauth2ClientCredentials);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ExternalArtifactsAccessConfigArtifact {\n");

		sb.append("    artifactUri: ").append(toIndentedString(artifactUri)).append("\n");
		sb.append("    overrideUri: ").append(toIndentedString(overrideUri)).append("\n");
		sb.append("    authType: ").append(toIndentedString(authType)).append("\n");
		sb.append("    username: ").append(toIndentedString(username)).append("\n");
		sb.append("    password: ").append(toIndentedString(password)).append("\n");
		sb.append("    paramsOauth2ClientCredentials: ").append(toIndentedString(paramsOauth2ClientCredentials)).append("\n");
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
