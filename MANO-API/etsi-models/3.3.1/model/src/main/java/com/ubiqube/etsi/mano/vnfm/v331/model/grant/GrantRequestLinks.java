package com.ubiqube.etsi.mano.vnfm.v331.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this request. 
 */
@Schema(description = "Links to resources related to this request. ")
@Validated


public class GrantRequestLinks   {
  @JsonProperty("vnfLcmOpOcc")
  private Link vnfLcmOpOcc = null;

  @JsonProperty("vnfInstance")
  private Link vnfInstance = null;

  public GrantRequestLinks vnfLcmOpOcc(Link vnfLcmOpOcc) {
    this.vnfLcmOpOcc = vnfLcmOpOcc;
    return this;
  }

  /**
   * Get vnfLcmOpOcc
   * @return vnfLcmOpOcc
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getVnfLcmOpOcc() {
    return vnfLcmOpOcc;
  }

  public void setVnfLcmOpOcc(Link vnfLcmOpOcc) {
    this.vnfLcmOpOcc = vnfLcmOpOcc;
  }

  public GrantRequestLinks vnfInstance(Link vnfInstance) {
    this.vnfInstance = vnfInstance;
    return this;
  }

  /**
   * Get vnfInstance
   * @return vnfInstance
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getVnfInstance() {
    return vnfInstance;
  }

  public void setVnfInstance(Link vnfInstance) {
    this.vnfInstance = vnfInstance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GrantRequestLinks grantRequestLinks = (GrantRequestLinks) o;
    return Objects.equals(this.vnfLcmOpOcc, grantRequestLinks.vnfLcmOpOcc) &&
        Objects.equals(this.vnfInstance, grantRequestLinks.vnfInstance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfLcmOpOcc, vnfInstance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GrantRequestLinks {\n");
    
    sb.append("    vnfLcmOpOcc: ").append(toIndentedString(vnfLcmOpOcc)).append("\n");
    sb.append("    vnfInstance: ").append(toIndentedString(vnfInstance)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
