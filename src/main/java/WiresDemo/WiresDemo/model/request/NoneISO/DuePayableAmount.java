
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
    "newApi_amount",
    "newApi_currency"
})
public class DuePayableAmount {

    @JsonProperty("newApi_amount")
    private String newApiAmount;
    @JsonProperty("newApi_currency")
    private String newApiCurrency;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("newApi_amount")
    public String getNewApiAmount() {
        return newApiAmount;
    }

    @JsonProperty("newApi_amount")
    public void setNewApiAmount(String newApiAmount) {
        this.newApiAmount = newApiAmount;
    }

    @JsonProperty("newApi_currency")
    public String getNewApiCurrency() {
        return newApiCurrency;
    }

    @JsonProperty("newApi_currency")
    public void setNewApiCurrency(String newApiCurrency) {
        this.newApiCurrency = newApiCurrency;
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
