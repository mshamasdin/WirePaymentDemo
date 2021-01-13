package WiresDemo.WiresDemo.model.response;

import WiresDemo.WiresDemo.model.GroupHeader;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wire_payment_status_report {
    GroupHeader group_header;
    ArrayList< TransactionInformationStatus > transaction_information_and_status = new ArrayList < TransactionInformationStatus > ();
}
