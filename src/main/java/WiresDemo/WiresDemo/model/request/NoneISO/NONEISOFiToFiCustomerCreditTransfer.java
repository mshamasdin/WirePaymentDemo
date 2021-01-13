
package WiresDemo.WiresDemo.model.request.NoneISO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "credit_transfer_transaction_information"
})
public class NONEISOFiToFiCustomerCreditTransfer {

    @JsonProperty("credit_transfer_transaction_information")
    private List<NoneISOCreditTransferTransactionInformation> noneISOCreditTransferTransactionInformation = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("credit_transfer_transaction_information")
    public List<NoneISOCreditTransferTransactionInformation> getNoneISOCreditTransferTransactionInformation() {
        return noneISOCreditTransferTransactionInformation;
    }

    @JsonProperty("credit_transfer_transaction_information")
    public void setNoneISOCreditTransferTransactionInformation(List<NoneISOCreditTransferTransactionInformation> noneISOCreditTransferTransactionInformation) {
        this.noneISOCreditTransferTransactionInformation = noneISOCreditTransferTransactionInformation;
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
