package WiresDemo.WiresDemo;

import WiresDemo.WiresDemo.model.BeneficiaryMasterData;
import WiresDemo.WiresDemo.model.SanctionsListData;
import WiresDemo.WiresDemo.model.request.NoneISO.AdjustmentAmountAndReason;
import WiresDemo.WiresDemo.model.request.NoneISO.NoneISO;
import WiresDemo.WiresDemo.model.request.NoneISO.NoneISOCreditTransferTransactionInformation;
import WiresDemo.WiresDemo.model.request.NoneISO.Structured;
import WiresDemo.WiresDemo.model.request.Pacs008.Pacs008;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.field.Field;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    public List<String> validate(SwiftMessage MT) throws IOException {
        validateMessageType(MT);
        isSenderReferenceExists(MT);
        isBankOperationCodeExists(MT);
        if(!validateSanctionClearing(MT)){
            return infractions;
        }
        validateSenderTxData(MT);
        validatePayerInfo(MT);
        System.out.println("----------List of Infractions Below: ---------");
        System.out.println(infractions);
        return infractions;
    }

    private void validatePayerInfo(SwiftMessage mt) {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = new PaymentRequestValidator().getClass().getClassLoader();
        try {
            File file = new File(classLoader.getResource("beneficiary_master_data.json").getFile());
            ObjectMapper om = new ObjectMapper();
            List<BeneficiaryMasterData> masterDataList = om.readValue(new String(Files.readAllBytes(file.toPath())), new TypeReference<List<BeneficiaryMasterData>>() {
            });
            Map<String, BeneficiaryMasterData> accounts = masterDataList.stream().collect(Collectors.toMap(BeneficiaryMasterData::getAccountNumber, BeneficiaryMasterData -> BeneficiaryMasterData));
            if (mt.getBlock4() != null) {
                if (mt.getBlock4().getFieldByName("50K") != null) {
                    Field senderDetails = mt.getBlock4().getFieldByName("50K");
                    if (!senderDetails.isEmpty()) {
                        List<String> components = senderDetails.getComponents();
                        //validate sender's account number
                        if (accounts.containsKey(components.get(0))) {
                            BeneficiaryMasterData masterData = accounts.get(components.get(0));
                            //validate sender's name
                            if (!StringUtils.equalsIgnoreCase(components.get(1), masterData.getName())) {
                                infractions.add("E347B");
                            }
                            //validate sender's address
                            if (!(StringUtils.equalsIgnoreCase(components.get(2), masterData.getAddressLine1()) &&
                                    StringUtils.equalsIgnoreCase(components.get(3), masterData.getAddressLine2()) &&
                                    StringUtils.equalsIgnoreCase(components.get(4), masterData.getCountry()))) {
                                infractions.add("E347C");
                            }
                            //validate amount with funds for insufficient balance in sender's master bank details
                            if (mt.getBlock4().getFieldByName("33B") != null) {
                                Field sendersAmount = mt.getBlock4().getFieldByName("33B");
                                if (!sendersAmount.isEmpty()) {
                                    NumberFormat curFormatter = NumberFormat.getNumberInstance(getLocale(sendersAmount.getComponents().get(0)));
                                    try {
                                        if (curFormatter.parse(sendersAmount.getComponents().get(1)).doubleValue() > curFormatter.parse(masterData.getTotalAmount()).doubleValue()) {
                                            infractions.add("E347D");
                                        }
                                    } catch (ParseException e) {
                                        infractions.add("E348");
                                    }

                                }
                            }
                        } else {
                            infractions.add("E347");
                        }
                    }
                }
            }
        } catch (IOException e) {
            infractions.add("E348");
        }
    }

    private boolean validateSanctionClearing(SwiftMessage mt) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = new PaymentRequestValidator().getClass().getClassLoader();
        /*try {*/
        File file = new File(classLoader.getResource("sanctions_list.json").getFile());
        ObjectMapper om = new ObjectMapper();
        List<SanctionsListData> sanctionList = om.readValue(new String(Files.readAllBytes(file.toPath())), new TypeReference<List<SanctionsListData>>() {
        });
        if (mt.getBlock4() != null) {
            if (mt.getBlock4().getFieldByName("59") != null) {
                Field receiverDetails = mt.getBlock4().getFieldByName("59");
                if (!receiverDetails.isEmpty()) {
                    List<String> rxComp = receiverDetails.getComponents().stream().filter(Objects::nonNull ).collect(Collectors.toList());
                    System.out.println(">>>>");
                    System.out.println(rxComp);
                    boolean sanctionDtlsmatch = sanctionList.stream().anyMatch(sanctionData -> {
                        if((rxComp.stream().anyMatch(t -> StringUtils.containsIgnoreCase(t,sanctionData.getName()))) &&
                                (rxComp.stream().anyMatch(t -> StringUtils.containsIgnoreCase(t,sanctionData.getCountry())))) {
                            infractions.add("MT019A");
                            return true;
                        }
                        return false;
                    });
                    if(sanctionDtlsmatch) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static Locale getLocale(String strCode) {
        for (Locale locale : NumberFormat.getAvailableLocales()) {
            String code = NumberFormat.getCurrencyInstance(locale).getCurrency().getCurrencyCode();
            if (strCode.equals(code)) {
                return locale;
            }
        }
        return null;
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

    private boolean validateSenderTxData(SwiftMessage mt) {
        String cur32ACode = "";
        if (mt.getBlock4() != null) {
            if (mt.getBlock4().getFieldByName("32A") != null) {
                Field senderTx = mt.getBlock4().getFieldByName("32A");
                if (!senderTx.isEmpty()) {
                    List<String> components = senderTx.getComponents();
                    //validate date
                    if (!GenericValidator.isDate(components.get(0), "yyMMdd", true)) {
                        infractions.add("T50");
                    }
                    if (StringUtils.isNotBlank(components.get(1))) {
                        cur32ACode = components.get(1);
                    }
                    //validate Amount
                    final String regExp = "[0-9]+([,.][0-9]{1,2})?";
                    String amount = components.get(2);
                    if (!amount.matches(regExp)) {
                        infractions.add("E346");
                    }
                }
            }

            //Validate existence of 36 if currency codes are not equal.
            if (mt.getBlock4().getFieldByName("33B") != null) {
                List<String> components = mt.getBlock4().getFieldByName("33B").getComponents();
                if (!components.isEmpty()) {
                    if (!StringUtils.equalsIgnoreCase(cur32ACode, components.get(0))) {
                        if (mt.getBlock4().getFieldByName("36").isEmpty()) {
                            infractions.add("D75");
                        }
                    }
                }
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
