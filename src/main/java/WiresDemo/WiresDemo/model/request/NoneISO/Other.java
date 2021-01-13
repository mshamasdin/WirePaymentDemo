
package WiresDemo.WiresDemo.model.request.NoneISO;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "newAPI_identification",
    "scheme_name",
    "issuer"
})
public class Other {

    @JsonProperty("newAPI_identification")
    private String newAPIIdentification;
    @JsonProperty("scheme_name")
    private SchemeName schemeName;
    @JsonProperty("issuer")
    private String issuer;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("newAPI_identification")
    public String getNewAPIIdentification() {
        return newAPIIdentification;
    }

    @JsonProperty("newAPI_identification")
    public void setNewAPIIdentification(String newAPIIdentification) {
        this.newAPIIdentification = newAPIIdentification;
    }

    @JsonProperty("scheme_name")
    public SchemeName getSchemeName() {
        return schemeName;
    }

    @JsonProperty("scheme_name")
    public void setSchemeName(SchemeName schemeName) {
        this.schemeName = schemeName;
    }

    @JsonProperty("issuer")
    public String getIssuer() {
        return issuer;
    }

    @JsonProperty("issuer")
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
