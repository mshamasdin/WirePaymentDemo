package WiresDemo.WiresDemo.model.request.Pacs008;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pacs008 {
    FIToFICstmrCdtTrf fi_to_fi_customer_credit_transfer;
    private String product_code;
    private String expiry_date;
    private String fi_account_id;
    private String account_holder_name;
    private String sender_account_identifier;
    SupplementaryInfo fraud_supplementary_info;
    TransferAuthentication payment_authentication;
    private String language;
}
