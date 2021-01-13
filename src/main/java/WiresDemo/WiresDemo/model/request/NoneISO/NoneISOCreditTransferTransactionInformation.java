
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
    "interbank_settlement_amount",
    "ultimate_debtor",
    "debtor_account",
    "creditor_account",
    "remittance_information"
})
public class NoneISOCreditTransferTransactionInformation {

    @JsonProperty("interbank_settlement_amount")
    private InterbankSettlementAmount interbankSettlementAmount;
    @JsonProperty("ultimate_debtor")
    private UltimateDebtor ultimateDebtor;
    @JsonProperty("debtor_account")
    private DebtorAccount debtorAccount;
    @JsonProperty("creditor_account")
    private CreditorAccount creditorAccount;
    @JsonProperty("remittance_information")
    private RemittanceInformation remittanceInformation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("interbank_settlement_amount")
    public InterbankSettlementAmount getInterbankSettlementAmount() {
        return interbankSettlementAmount;
    }

    @JsonProperty("interbank_settlement_amount")
    public void setInterbankSettlementAmount(InterbankSettlementAmount interbankSettlementAmount) {
        this.interbankSettlementAmount = interbankSettlementAmount;
    }

    @JsonProperty("ultimate_debtor")
    public UltimateDebtor getUltimateDebtor() {
        return ultimateDebtor;
    }

    @JsonProperty("ultimate_debtor")
    public void setUltimateDebtor(UltimateDebtor ultimateDebtor) {
        this.ultimateDebtor = ultimateDebtor;
    }

    @JsonProperty("debtor_account")
    public DebtorAccount getDebtorAccount() {
        return debtorAccount;
    }

    @JsonProperty("debtor_account")
    public void setDebtorAccount(DebtorAccount debtorAccount) {
        this.debtorAccount = debtorAccount;
    }

    @JsonProperty("creditor_account")
    public CreditorAccount getCreditorAccount() {
        return creditorAccount;
    }

    @JsonProperty("creditor_account")
    public void setCreditorAccount(CreditorAccount creditorAccount) {
        this.creditorAccount = creditorAccount;
    }

    @JsonProperty("remittance_information")
    public RemittanceInformation getRemittanceInformation() {
        return remittanceInformation;
    }

    @JsonProperty("remittance_information")
    public void setRemittanceInformation(RemittanceInformation remittanceInformation) {
        this.remittanceInformation = remittanceInformation;
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
