package com.ubiqube.etsi.mano.nfvo.v331.model.nsd;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nsd.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource. 
 */
@Schema(description = "Links to resources related to this resource. ")
@Validated


public class PnfdInfoLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("pnfd_content")
  private Link pnfdContent = null;

  public PnfdInfoLinks self(Link self) {
    this.self = self;
    return this;
  }

  /**
   * Get self
   * @return self
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getSelf() {
    return self;
  }

  public void setSelf(Link self) {
    this.self = self;
  }

  public PnfdInfoLinks pnfdContent(Link pnfdContent) {
    this.pnfdContent = pnfdContent;
    return this;
  }

  /**
   * Get pnfdContent
   * @return pnfdContent
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getPnfdContent() {
    return pnfdContent;
  }

  public void setPnfdContent(Link pnfdContent) {
    this.pnfdContent = pnfdContent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PnfdInfoLinks pnfdInfoLinks = (PnfdInfoLinks) o;
    return Objects.equals(this.self, pnfdInfoLinks.self) &&
        Objects.equals(this.pnfdContent, pnfdInfoLinks.pnfdContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, pnfdContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PnfdInfoLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    pnfdContent: ").append(toIndentedString(pnfdContent)).append("\n");
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
