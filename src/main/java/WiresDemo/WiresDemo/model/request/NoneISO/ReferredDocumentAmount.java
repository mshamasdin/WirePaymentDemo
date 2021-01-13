
package WiresDemo.WiresDemo.model.request.NoneISO;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "due_payable_amount",
    "adjustment_amount_and_reason",
    "remitted_amount"
})
public class ReferredDocumentAmount {

    @JsonProperty("due_payable_amount")
    private DuePayableAmount duePayableAmount;
    @JsonProperty("adjustment_amount_and_reason")
    private List<AdjustmentAmountAndReason> adjustmentAmountAndReason = null;
    @JsonProperty("remitted_amount")
    private RemittedAmount remittedAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("due_payable_amount")
    public DuePayableAmount getDuePayableAmount() {
        return duePayableAmount;
    }

    @JsonProperty("due_payable_amount")
    public void setDuePayableAmount(DuePayableAmount duePayableAmount) {
        this.duePayableAmount = duePayableAmount;
    }

    @JsonProperty("adjustment_amount_and_reason")
    public List<AdjustmentAmountAndReason> getAdjustmentAmountAndReason() {
        return adjustmentAmountAndReason;
    }

    @JsonProperty("adjustment_amount_and_reason")
    public void setAdjustmentAmountAndReason(List<AdjustmentAmountAndReason> adjustmentAmountAndReason) {
        this.adjustmentAmountAndReason = adjustmentAmountAndReason;
    }

    @JsonProperty("remitted_amount")
    public RemittedAmount getRemittedAmount() {
        return remittedAmount;
    }

    @JsonProperty("remitted_amount")
    public void setRemittedAmount(RemittedAmount remittedAmount) {
        this.remittedAmount = remittedAmount;
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
