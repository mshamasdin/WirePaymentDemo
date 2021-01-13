package WiresDemo.WiresDemo.model.request.Pacs008;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditTransferTransactionInformation {
    private PaymentIdentification payment_identification;
    private PaymentTypeInformation payment_type_information;
    private Amount interbank_settlement_amount;
    private String interbank_settlement_date;
    private String acceptance_datetime;
    private String charge_bearer;
    private Agent previous_instructing_agent_1;
    private Party ultimate_debtor;
    private Party initiating_party;
    private Party debtor;
    private Account debtor_account;
    private Agent debtor_agent;
    private Agent creditor_agent;
    private Party creditor;
    private Account creditor_account;
    private Party ultmate_creditor;
    private ArrayList<RelatedRemittanceInformation> related_remittance_information = new ArrayList<>();
}
