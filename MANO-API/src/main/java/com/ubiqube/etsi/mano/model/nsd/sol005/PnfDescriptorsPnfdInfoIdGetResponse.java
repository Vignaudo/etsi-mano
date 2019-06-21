package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class PnfDescriptorsPnfdInfoIdGetResponse {
  
  @ApiModelProperty(value = "")
  @Valid
  private PnfDescriptorsPnfdInfo pnfdInfo = null;
 /**
   * Get pnfdInfo
   * @return pnfdInfo
  **/
  @JsonProperty("PnfdInfo")
  public PnfDescriptorsPnfdInfo getPnfdInfo() {
    return pnfdInfo;
  }

  public void setPnfdInfo(PnfDescriptorsPnfdInfo pnfdInfo) {
    this.pnfdInfo = pnfdInfo;
  }

  public PnfDescriptorsPnfdInfoIdGetResponse pnfdInfo(PnfDescriptorsPnfdInfo pnfdInfo) {
    this.pnfdInfo = pnfdInfo;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PnfDescriptorsPnfdInfoIdGetResponse {\n");
    
    sb.append("    pnfdInfo: ").append(toIndentedString(pnfdInfo)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
