
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
    "NONE_ISO_fi_to_fi_customer_credit_transfer",
    "product_code",
    "fi_account_id",
    "newApi_account_holder_name",
    "sender_account_identifier",
    "fraud_supplementary_info"
})
public class NoneISO {

    @JsonProperty("NON_ISO_fi_to_fi_customer_credit_transfer")
    private NONEISOFiToFiCustomerCreditTransfer nONEISOFiToFiCustomerCreditTransfer;
    @JsonProperty("product_code")
    private String productCode;
    @JsonProperty("fi_account_id")
    private String fiAccountId;
    @JsonProperty("newApi_account_holder_name")
    private String newApiAccountHolderName;
    @JsonProperty("sender_account_identifier")
    private String senderAccountIdentifier;
    @JsonProperty("fraud_supplementary_info")
    private FraudSupplementaryInfo fraudSupplementaryInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("NON_ISO_fi_to_fi_customer_credit_transfer")
    public NONEISOFiToFiCustomerCreditTransfer getNONEISOFiToFiCustomerCreditTransfer() {
        return nONEISOFiToFiCustomerCreditTransfer;
    }

    @JsonProperty("NON_ISO_fi_to_fi_customer_credit_transfer")
    public void setNONEISOFiToFiCustomerCreditTransfer(NONEISOFiToFiCustomerCreditTransfer nONEISOFiToFiCustomerCreditTransfer) {
        this.nONEISOFiToFiCustomerCreditTransfer = nONEISOFiToFiCustomerCreditTransfer;
    }

    @JsonProperty("product_code")
    public String getProductCode() {
        return productCode;
    }

    @JsonProperty("product_code")
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @JsonProperty("fi_account_id")
    public String getFiAccountId() {
        return fiAccountId;
    }

    @JsonProperty("fi_account_id")
    public void setFiAccountId(String fiAccountId) {
        this.fiAccountId = fiAccountId;
    }

    @JsonProperty("newApi_account_holder_name")
    public String getNewApiAccountHolderName() {
        return newApiAccountHolderName;
    }

    @JsonProperty("newApi_account_holder_name")
    public void setNewApiAccountHolderName(String newApiAccountHolderName) {
        this.newApiAccountHolderName = newApiAccountHolderName;
    }

    @JsonProperty("sender_account_identifier")
    public String getSenderAccountIdentifier() {
        return senderAccountIdentifier;
    }

    @JsonProperty("sender_account_identifier")
    public void setSenderAccountIdentifier(String senderAccountIdentifier) {
        this.senderAccountIdentifier = senderAccountIdentifier;
    }

    @JsonProperty("fraud_supplementary_info")
    public FraudSupplementaryInfo getFraudSupplementaryInfo() {
        return fraudSupplementaryInfo;
    }

    @JsonProperty("fraud_supplementary_info")
    public void setFraudSupplementaryInfo(FraudSupplementaryInfo fraudSupplementaryInfo) {
        this.fraudSupplementaryInfo = fraudSupplementaryInfo;
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
