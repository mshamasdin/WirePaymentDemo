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
        "block1",
        "block2",
        "block3",
        "block4"
})
public class Data {

    @JsonProperty("block1")
    private Block1 block1;
    @JsonProperty("block2")
    private Block2 block2;
    @JsonProperty("block3")
    private Block3 block3;
    @JsonProperty("block4")
    private Block4 block4;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("block1")
    public Block1 getBlock1() {
        return block1;
    }

    @JsonProperty("block1")
    public void setBlock1(Block1 block1) {
        this.block1 = block1;
    }

    public Data withBlock1(Block1 block1) {
        this.block1 = block1;
        return this;
    }

    @JsonProperty("block2")
    public Block2 getBlock2() {
        return block2;
    }

    @JsonProperty("block2")
    public void setBlock2(Block2 block2) {
        this.block2 = block2;
    }

    public Data withBlock2(Block2 block2) {
        this.block2 = block2;
        return this;
    }

    @JsonProperty("block3")
    public Block3 getBlock3() {
        return block3;
    }

    @JsonProperty("block3")
    public void setBlock3(Block3 block3) {
        this.block3 = block3;
    }

    public Data withBlock3(Block3 block3) {
        this.block3 = block3;
        return this;
    }

    @JsonProperty("block4")
    public Block4 getBlock4() {
        return block4;
    }

    @JsonProperty("block4")
    public void setBlock4(Block4 block4) {
        this.block4 = block4;
    }

    public Data withBlock4(Block4 block4) {
        this.block4 = block4;
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

    public Data withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
