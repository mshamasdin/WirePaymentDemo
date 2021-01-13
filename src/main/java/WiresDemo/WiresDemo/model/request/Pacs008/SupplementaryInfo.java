package WiresDemo.WiresDemo.model.request.Pacs008;

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
public class SupplementaryInfo {
    private String customer_ip_address;
    private String customer_card_number;
    private String account_creation_date;
    private String customer_device_finger_print;
    private String customer_authentication_method;
}
