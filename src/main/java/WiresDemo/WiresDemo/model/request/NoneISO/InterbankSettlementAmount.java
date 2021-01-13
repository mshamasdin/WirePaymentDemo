
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
    "newAPI_amount",
    "newAPI_currency"
})
public class InterbankSettlementAmount {

    @JsonProperty("newAPI_amount")
    private String newAPIAmount;
    @JsonProperty("newAPI_currency")
    private String newAPICurrency;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("newAPI_amount")
    public String getNewAPIAmount() {
        return newAPIAmount;
    }

    @JsonProperty("newAPI_amount")
    public void setNewAPIAmount(String newAPIAmount) {
        this.newAPIAmount = newAPIAmount;
    }

    @JsonProperty("newAPI_currency")
    public String getNewAPICurrency() {
        return newAPICurrency;
    }

    @JsonProperty("newAPI_currency")
    public void setNewAPICurrency(String newAPICurrency) {
        this.newAPICurrency = newAPICurrency;
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
