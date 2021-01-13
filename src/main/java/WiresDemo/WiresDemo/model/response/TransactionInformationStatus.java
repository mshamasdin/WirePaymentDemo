package WiresDemo.WiresDemo.model.response;

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
public class TransactionInformationStatus {

    OriginalGroupInformation original_group_information;
    String original_instruction_identification;
    String original_end_to_end_identification;
    String original_transaction_identification;
    String transaction_status;
    ArrayList< StatusReasonInformation > status_reason_information = new ArrayList < StatusReasonInformation > ();
    String acceptance_datetime;
    String clearing_system_reference;

}
