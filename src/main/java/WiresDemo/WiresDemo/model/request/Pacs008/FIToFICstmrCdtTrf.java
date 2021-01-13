package WiresDemo.WiresDemo.model.request.Pacs008;

import WiresDemo.WiresDemo.model.GroupHeader;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FIToFICstmrCdtTrf {
    GroupHeader group_header;
    ArrayList< CreditTransferTransactionInformation > credit_transfer_transaction_information = new ArrayList<>();
}
