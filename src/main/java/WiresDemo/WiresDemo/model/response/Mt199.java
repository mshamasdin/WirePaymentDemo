package WiresDemo.WiresDemo.model.response;

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
public class Mt199 {
    Wire_payment_status_report wire_payment_status_report;
    private String payment_status;
    Fraud_check_result fraud_check_result;
}
