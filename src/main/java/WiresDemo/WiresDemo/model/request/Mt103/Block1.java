package WiresDemo.WiresDemo.model.request.Mt103;

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
        "applicationId",
        "serviceId",
        "logicalTerminal",
        "sessionNumber",
        "sequenceNumber"
})
public class Block1 {

    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("serviceId")
    private String serviceId;
    @JsonProperty("logicalTerminal")
    private String logicalTerminal;
    @JsonProperty("sessionNumber")
    private String sessionNumber;
    @JsonProperty("sequenceNumber")
    private String sequenceNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("applicationId")
    public String getApplicationId() {
        return applicationId;
    }

    @JsonProperty("applicationId")
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Block1 withApplicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    @JsonProperty("serviceId")
    public String getServiceId() {
        return serviceId;
    }

    @JsonProperty("serviceId")
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Block1 withServiceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    @JsonProperty("logicalTerminal")
    public String getLogicalTerminal() {
        return logicalTerminal;
    }

    @JsonProperty("logicalTerminal")
    public void setLogicalTerminal(String logicalTerminal) {
        this.logicalTerminal = logicalTerminal;
    }

    public Block1 withLogicalTerminal(String logicalTerminal) {
        this.logicalTerminal = logicalTerminal;
        return this;
    }

    @JsonProperty("sessionNumber")
    public String getSessionNumber() {
        return sessionNumber;
    }

    @JsonProperty("sessionNumber")
    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public Block1 withSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
        return this;
    }

    @JsonProperty("sequenceNumber")
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    @JsonProperty("sequenceNumber")
    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Block1 withSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Block1 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}