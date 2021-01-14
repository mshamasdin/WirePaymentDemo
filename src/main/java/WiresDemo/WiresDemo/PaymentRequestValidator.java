package WiresDemo.WiresDemo;

import WiresDemo.WiresDemo.model.request.NoneISO.AdjustmentAmountAndReason;
import WiresDemo.WiresDemo.model.request.NoneISO.NoneISO;
import WiresDemo.WiresDemo.model.request.NoneISO.NoneISOCreditTransferTransactionInformation;
import WiresDemo.WiresDemo.model.request.NoneISO.Structured;
import WiresDemo.WiresDemo.model.request.Pacs008.Pacs008;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.field.Field;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.apache.commons.validator.GenericValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Component
public class PaymentRequestValidator {

    List<String> infractions = new ArrayList<>();

    public List<String> validate(Pacs008 pacs008) {
        accountHolderNameExists(pacs008);
        debtorAccountIdentifierExists(pacs008);
        validProprietaryValueForClearingSystem(pacs008);
        return infractions;
    }

    public List<String> validate(NoneISO noneISO) {
        //debtorAccountIdentifierExists(noneISO);
        invalidAmountPayment(noneISO);
        unsupportedCurrency(noneISO);
        return infractions;
    }

    public List<String> validate(SwiftMessage MT) {
        validateMessageType(MT);
        isSenderReferenceExists(MT);
        isBankOperationCodeExists(MT);
        validateSenderTxData(MT);
        return infractions;
    }

    private boolean validateMessageType(SwiftMessage mt) {
        if(StringUtils.isNotBlank(mt.getType()) && StringUtils.equalsIgnoreCase(mt.getType(),"103")){
            return true;
        }
        infractions.add("T26");
        return false;
    }

    private boolean isSenderReferenceExists(SwiftMessage mt) {
        if(mt.getBlock1() != null && mt.getBlock1().getApplicationId() != null && mt.getBlock1().getServiceId() != null
        && mt.getBlock1().getLogicalTerminal() != null && mt.getBlock1().getSessionNumber() != null && mt.getBlock1().getSequenceNumber() != null){
            return true;
        }
        infractions.add("T26");
        return false;
    }

    private boolean isBankOperationCodeExists(SwiftMessage mt) {
        if(mt.getBlock4() != null && mt.getBlock4().getFieldByName("23B") != null && StringUtils.isNotBlank(mt.getBlock4().getFieldByName("23B").getValue())){
            return true;
        }
        infractions.add("T36");
        return false;
    }

    private boolean validateSenderTxData(SwiftMessage MT103) {
        if(MT103.getBlock4() != null && MT103.getBlock4().getFieldByName("32A") != null){
            Field senderTx = MT103.getBlock4().getFieldByName("32A");
            if(!senderTx.isEmpty()) {
                List<String> components = senderTx.getComponents();
                //validate date
                if(GenericValidator.isDate(components.get(0),"YYMMdd", true)) {
                    return true;
                }
                infractions.add("T50");
                //valdiate currency
                //validate amount
            }
            return true;
        }
        return false;
    }


    private boolean unsupportedCurrency(NoneISO noneISO) {
        for(NoneISOCreditTransferTransactionInformation credit : noneISO.getNONEISOFiToFiCustomerCreditTransfer().getNoneISOCreditTransferTransactionInformation()) {
            if(!credit.getInterbankSettlementAmount().getNewAPICurrency().equalsIgnoreCase("CAD")
                    && !credit.getInterbankSettlementAmount().getNewAPICurrency().equalsIgnoreCase("USD")) {
                infractions.add("461");
                return false;
            }
            for (Structured structured : credit.getRemittanceInformation().getStructured()){
                if(!structured.getReferredDocumentAmount().getDuePayableAmount().getNewApiCurrency().equalsIgnoreCase("CAD")
                && !structured.getReferredDocumentAmount().getDuePayableAmount().getNewApiCurrency().equalsIgnoreCase("USD")) {
                    infractions.add("461");
                    return false;
                }
                if(!structured.getReferredDocumentAmount().getRemittedAmount().getNewApiCurrency().equalsIgnoreCase("CAD")
                && !structured.getReferredDocumentAmount().getRemittedAmount().getNewApiCurrency().equalsIgnoreCase("USD")) {
                    infractions.add("461");
                    return false;
                }
                for (AdjustmentAmountAndReason adjusted : structured.getReferredDocumentAmount().getAdjustmentAmountAndReason()) {
                    if(!adjusted.getAmount().getNewApiCurrency().equalsIgnoreCase("CAD")
                            && !adjusted.getAmount().getNewApiCurrency().equalsIgnoreCase("USD") ) {
                        infractions.add("461");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean invalidAmountPayment(NoneISO noneISO) {
        for(NoneISOCreditTransferTransactionInformation credit : noneISO.getNONEISOFiToFiCustomerCreditTransfer().getNoneISOCreditTransferTransactionInformation()) {
            if(!NumberUtils.isNumber(credit.getInterbankSettlementAmount().getNewAPIAmount())) {
                infractions.add("346");
                return false;
            }
            for (Structured structured : credit.getRemittanceInformation().getStructured()){
                if(!NumberUtils.isNumber(structured.getReferredDocumentAmount().getDuePayableAmount().getNewApiAmount())) {
                    infractions.add("346");
                    return false;
                }
                if(!NumberUtils.isNumber(structured.getReferredDocumentAmount().getRemittedAmount().getNewApiAmount())) {
                    infractions.add("346");
                    return false;
                }
                for (AdjustmentAmountAndReason adjusted : structured.getReferredDocumentAmount().getAdjustmentAmountAndReason()) {
                    if(!NumberUtils.isNumber(adjusted.getAmount().getNewApiAmount())) {
                        infractions.add("346");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean validProprietaryValueForClearingSystem(Pacs008 pacs008) {
        if(!pacs008.getFi_to_fi_customer_credit_transfer().getGroup_header().getSettlement_information().getClearing_system().getProprietary().equals("ETR")) {
            infractions.add("2007");
            return false;
        }
        return true;
    }


    private boolean debtorAccountIdentifierExists(NoneISO noneISO) {
        if(noneISO.getNONEISOFiToFiCustomerCreditTransfer().getNoneISOCreditTransferTransactionInformation().get(0).getDebtorAccount().getIdentification().getOther().getNewAPIIdentification() == null ||
                noneISO.getNONEISOFiToFiCustomerCreditTransfer().getNoneISOCreditTransferTransactionInformation().get(0).getDebtorAccount().getIdentification().getOther().getNewAPIIdentification().equals("")) {
            infractions.add("571");
            return false;
        }
        validDebtorAccountIdentifierFormat(noneISO);
        return true;
    }

    private boolean validDebtorAccountIdentifierFormat(NoneISO noneISO) {
        String accFormat = noneISO.getNONEISOFiToFiCustomerCreditTransfer().getNoneISOCreditTransferTransactionInformation().get(0).getDebtorAccount().getIdentification().getOther().getNewAPIIdentification();
        Pattern p = Pattern.compile("[\\d]{3}-[\\d]{5}-[\\d]{0,24}");
        if (!p.matcher(accFormat).matches()) {
            infractions.add("566");
            return false;
        }

        return true;
    }

    private boolean debtorAccountIdentifierExists(Pacs008 pacs008) {
        if(pacs008.getFi_to_fi_customer_credit_transfer().getCredit_transfer_transaction_information().get(0).getDebtor_account().getIdentification().getOther().getIdentification() == null ||
                pacs008.getFi_to_fi_customer_credit_transfer().getCredit_transfer_transaction_information().get(0).getDebtor_account().getIdentification().getOther().getIdentification() == "") {
            infractions.add("571");
            return false;
        }
        validDebtorAccountIdentifierFormat(pacs008);
        return true;
    }

    private boolean validDebtorAccountIdentifierFormat(Pacs008 pacs008) {
        String accFormat = pacs008.getFi_to_fi_customer_credit_transfer().getCredit_transfer_transaction_information().get(0).getDebtor_account().getIdentification().getOther().getIdentification();
        Pattern p = Pattern.compile("[\\d]{3}-[\\d]{5}-[\\d]{0,24}");
        if (!p.matcher(accFormat).matches()) {
            infractions.add("566");
            return false;
        }

        return true;
    }

    private boolean accountHolderNameExists(Pacs008 pacs008) {
        if (pacs008.getAccount_holder_name() == null || pacs008.getAccount_holder_name().equals("")) {
            infractions.add("441");
            return false;
        }
        return true;
    }

    public void clearErrorCodes() {
        infractions.clear();
    }



}
