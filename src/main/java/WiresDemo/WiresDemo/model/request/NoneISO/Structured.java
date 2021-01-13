
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
    "referred_document_amount"
})
public class Structured {

    @JsonProperty("referred_document_amount")
    private ReferredDocumentAmount referredDocumentAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("referred_document_amount")
    public ReferredDocumentAmount getReferredDocumentAmount() {
        return referredDocumentAmount;
    }

    @JsonProperty("referred_document_amount")
    public void setReferredDocumentAmount(ReferredDocumentAmount referredDocumentAmount) {
        this.referredDocumentAmount = referredDocumentAmount;
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
