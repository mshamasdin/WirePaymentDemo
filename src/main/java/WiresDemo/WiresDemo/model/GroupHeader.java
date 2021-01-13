package WiresDemo.WiresDemo.model;

import WiresDemo.WiresDemo.model.request.Pacs008.Agent;
import WiresDemo.WiresDemo.model.request.Pacs008.SettlementInformation;
import WiresDemo.WiresDemo.model.response.Related_reference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupHeader {
    private String message_identification;
    private String creation_datetime;
    private String number_of_transactions;
    SettlementInformation settlement_information;
    Agent instructing_agent;
    Agent instructed_agent;
    Related_reference related_reference;
}
