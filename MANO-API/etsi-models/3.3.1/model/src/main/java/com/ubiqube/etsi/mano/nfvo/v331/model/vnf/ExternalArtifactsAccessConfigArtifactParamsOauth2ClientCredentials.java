package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Parameters for authentication/authorization using OAUTH2_CLIENT_CREDENTIALS.
 */
@ApiModel(description = "Parameters for authentication/authorization using OAUTH2_CLIENT_CREDENTIALS. ")
@Validated
public class ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials {
	@JsonProperty("clientId")
	private String clientId = null;

	@JsonProperty("clientPassword")
	private String clientPassword = null;

	@JsonProperty("tokenEndpoint")
	private String tokenEndpoint = null;

	public ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials clientId(final String clientId) {
		this.clientId = clientId;
		return this;
	}

	/**
	 * Client identifier to be used in the access token request of the OAuth 2.0
	 * client credentials grant type.
	 *
	 * @return clientId
	 **/
	@ApiModelProperty(required = true, value = "Client identifier to be used in the access token request of the OAuth 2.0 client credentials grant type. ")
	@NotNull

	public String getClientId() {
		return clientId;
	}

	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	public ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials clientPassword(final String clientPassword) {
		this.clientPassword = clientPassword;
		return this;
	}

	/**
	 * Client password to be used in the access token request of the OAuth 2.0
	 * client credentials grant type. Shall not be present in response bodies.
	 *
	 * @return clientPassword
	 **/
	@ApiModelProperty(required = true, value = "Client password to be used in the access token request of the OAuth 2.0 client credentials grant type. Shall not be present in response bodies. ")
	@NotNull

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(final String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials tokenEndpoint(final String tokenEndpoint) {
		this.tokenEndpoint = tokenEndpoint;
		return this;
	}

	/**
	 * Get tokenEndpoint
	 *
	 * @return tokenEndpoint
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getTokenEndpoint() {
		return tokenEndpoint;
	}

	public void setTokenEndpoint(final String tokenEndpoint) {
		this.tokenEndpoint = tokenEndpoint;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials externalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials = (ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials) o;
		return Objects.equals(this.clientId, externalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials.clientId) &&
				Objects.equals(this.clientPassword, externalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials.clientPassword) &&
				Objects.equals(this.tokenEndpoint, externalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials.tokenEndpoint);
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, clientPassword, tokenEndpoint);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials {\n");

		sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
		sb.append("    clientPassword: ").append(toIndentedString(clientPassword)).append("\n");
		sb.append("    tokenEndpoint: ").append(toIndentedString(tokenEndpoint)).append("\n");
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
