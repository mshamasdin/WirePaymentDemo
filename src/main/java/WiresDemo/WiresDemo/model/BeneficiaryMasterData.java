package WiresDemo.WiresDemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryMasterData {

    private String name;
    private String addressLine1;
    private String addressLine2;
    private String postalCode;
    private String province;
    private String country;
    private String accountNumber;
    private BigDecimal withdrawLimit;
    private String totalAmount;
    private String currencyCode;
}
