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
        "receiverAddress",
        "deliveryMonitoring",
        "obsolescencePeriod",
        "messagePriority",
        "messageType",
        "direction"
})
public class Block2 {

    @JsonProperty("receiverAddress")
    private String receiverAddress;
    @JsonProperty("deliveryMonitoring")
    private String deliveryMonitoring;
    @JsonProperty("obsolescencePeriod")
    private String obsolescencePeriod;
    @JsonProperty("messagePriority")
    private String messagePriority;
    @JsonProperty("messageType")
    private String messageType;
    @JsonProperty("direction")
    private String direction;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("receiverAddress")
    public String getReceiverAddress() {
        return receiverAddress;
    }

    @JsonProperty("receiverAddress")
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Block2 withReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    @JsonProperty("deliveryMonitoring")
    public String getDeliveryMonitoring() {
        return deliveryMonitoring;
    }

    @JsonProperty("deliveryMonitoring")
    public void setDeliveryMonitoring(String deliveryMonitoring) {
        this.deliveryMonitoring = deliveryMonitoring;
    }

    public Block2 withDeliveryMonitoring(String deliveryMonitoring) {
        this.deliveryMonitoring = deliveryMonitoring;
        return this;
    }

    @JsonProperty("obsolescencePeriod")
    public String getObsolescencePeriod() {
        return obsolescencePeriod;
    }

    @JsonProperty("obsolescencePeriod")
    public void setObsolescencePeriod(String obsolescencePeriod) {
        this.obsolescencePeriod = obsolescencePeriod;
    }

    public Block2 withObsolescencePeriod(String obsolescencePeriod) {
        this.obsolescencePeriod = obsolescencePeriod;
        return this;
    }

    @JsonProperty("messagePriority")
    public String getMessagePriority() {
        return messagePriority;
    }

    @JsonProperty("messagePriority")
    public void setMessagePriority(String messagePriority) {
        this.messagePriority = messagePriority;
    }

    public Block2 withMessagePriority(String messagePriority) {
        this.messagePriority = messagePriority;
        return this;
    }

    @JsonProperty("messageType")
    public String getMessageType() {
        return messageType;
    }

    @JsonProperty("messageType")
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Block2 withMessageType(String messageType) {
        this.messageType = messageType;
        return this;
    }

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Block2 withDirection(String direction) {
        this.direction = direction;
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

    public Block2 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
