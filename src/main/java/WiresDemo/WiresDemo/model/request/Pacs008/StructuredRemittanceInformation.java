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
public class StructuredRemittanceInformation {
    private ArrayList<ReferredDocumentInformation> referred_document_information = new ArrayList<>();
    private ReferredDocumentAmount referred_document_amount;
    private CreditorReferenceInformation creditor_reference_information;
    private Party invoicer;
    private Party invoicee;
    private ArrayList<String> additional_remittance_information = new ArrayList<>();
}
