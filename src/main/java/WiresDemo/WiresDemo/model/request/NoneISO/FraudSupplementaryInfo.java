
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
    "customer_ip_address",
    "customer_card_number",
    "account_creation_date",
    "customer_device_finger_print",
    "customer_authentication_method"
})
public class FraudSupplementaryInfo {

    @JsonProperty("customer_ip_address")
    private String customerIpAddress;
    @JsonProperty("customer_card_number")
    private String customerCardNumber;
    @JsonProperty("account_creation_date")
    private String accountCreationDate;
    @JsonProperty("customer_device_finger_print")
    private String customerDeviceFingerPrint;
    @JsonProperty("customer_authentication_method")
    private String customerAuthenticationMethod;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("customer_ip_address")
    public String getCustomerIpAddress() {
        return customerIpAddress;
    }

    @JsonProperty("customer_ip_address")
    public void setCustomerIpAddress(String customerIpAddress) {
        this.customerIpAddress = customerIpAddress;
    }

    @JsonProperty("customer_card_number")
    public String getCustomerCardNumber() {
        return customerCardNumber;
    }

    @JsonProperty("customer_card_number")
    public void setCustomerCardNumber(String customerCardNumber) {
        this.customerCardNumber = customerCardNumber;
    }

    @JsonProperty("account_creation_date")
    public String getAccountCreationDate() {
        return accountCreationDate;
    }

    @JsonProperty("account_creation_date")
    public void setAccountCreationDate(String accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    @JsonProperty("customer_device_finger_print")
    public String getCustomerDeviceFingerPrint() {
        return customerDeviceFingerPrint;
    }

    @JsonProperty("customer_device_finger_print")
    public void setCustomerDeviceFingerPrint(String customerDeviceFingerPrint) {
        this.customerDeviceFingerPrint = customerDeviceFingerPrint;
    }

    @JsonProperty("customer_authentication_method")
    public String getCustomerAuthenticationMethod() {
        return customerAuthenticationMethod;
    }

    @JsonProperty("customer_authentication_method")
    public void setCustomerAuthenticationMethod(String customerAuthenticationMethod) {
        this.customerAuthenticationMethod = customerAuthenticationMethod;
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
