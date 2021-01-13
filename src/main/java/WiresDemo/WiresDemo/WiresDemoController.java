package WiresDemo.WiresDemo;

import WiresDemo.WiresDemo.model.GroupHeader;
import WiresDemo.WiresDemo.model.request.NoneISO.NoneISO;
import WiresDemo.WiresDemo.model.request.Pacs008.Agent;
import WiresDemo.WiresDemo.model.request.Pacs008.ClearingSystemMemberIdentification;
import WiresDemo.WiresDemo.model.request.Pacs008.FinancialIntitutionIdentification;
import WiresDemo.WiresDemo.model.request.Pacs008.Pacs008;
import WiresDemo.WiresDemo.model.request.Mt103.WireMT103Payload;
import WiresDemo.WiresDemo.model.response.*;
import WiresDemo.WiresDemo.model.response.*;
import com.google.gson.Gson;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prowidesoftware.swift.io.parser.SwiftParser;
import com.prowidesoftware.swift.model.SwiftMessage;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class WiresDemoController {

    @Autowired
    private PaymentRequestValidator paymentRequestValidator;

    @GetMapping("/test")
    public String test() {
        return "Yes";
    }

    @PostMapping(value = "/sendPaymentRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 500, message = "Internal Server Error")})
    public Pacs002 validatePayment(@Valid @RequestBody Pacs008 pacs008, BindingResult bindingResult) {

        List<String> errorCodes = this.paymentRequestValidator.validate(pacs008);
        return produceResponse(pacs008, errorCodes);
    }

    @PostMapping(value = "/sendWirePaymentRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = ""), @ApiResponse(code = 500, message = "Internal Server Error")})
    public Mt199 validateWirePayment(@Valid @RequestBody WireMT103Payload wireMT103Payload) throws IOException {
        log.info("In service");
        log.info("WireMT103"+ wireMT103Payload.getPayLoad());
        //to JSON
        SwiftParser parser = new SwiftParser(wireMT103Payload.getPayLoad());
        SwiftMessage MT = parser.message();

        log.info("WireMT103 JSON"+ MT.toJson());

        List<String> errorCodes = this.paymentRequestValidator.validate(MT);
        return produceResponse(wireMT103Payload,MT,errorCodes);
    }

    private Mt199 produceResponse(@Valid WireMT103Payload wireMT103Payload, SwiftMessage mt103, List<String> errorCodes) {
        String filename = "mt199_sample.json";
        ClassLoader classLoader = new WiresDemoApplication().getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        String json = null;
        try {
            json = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Mt199 mt199 = new Gson().fromJson(json, Mt199.class);

        GroupHeader group_header = GroupHeader.builder()
                .message_identification(mt103.getBlock1().getLogicalTerminal())
                .creation_datetime("2020/23/07")
                .related_reference(new Related_reference().builder().swift_msg_type(mt103.getType()).build())
                .build();
        mt199.getWire_payment_status_report().setGroup_header(group_header);

        ArrayList<TransactionInformationStatus> statusList = new ArrayList<>();
        TransactionInformationStatus transactionInformationStatus = TransactionInformationStatus.builder()
                .original_group_information(OriginalGroupInformation.builder()
                        .original_message_identification(mt103.getBlock1().getLogicalTerminal())
                        .original_message_name_identification(mt103.getType())
                        .build())
                .transaction_status("PDNG")
                .acceptance_datetime("acceptance datetime")
                .clearing_system_reference("clearing_system_reference")
                .build();
        ArrayList<StatusReasonInformation> statusReasonInformation = new ArrayList<>();
        statusReasonInformation.add(0, StatusReasonInformation.builder()
                .reason(Reason.builder().proprietary("0").build()).build());
        transactionInformationStatus.setStatus_reason_information(statusReasonInformation);
        mt199.getWire_payment_status_report().getTransaction_information_and_status().set(0, transactionInformationStatus);

        if(errorCodes != null && !errorCodes.isEmpty()) {
            statusList = new ArrayList<>();
            TransactionInformationStatus status = mt199.getWire_payment_status_report().getTransaction_information_and_status().get(0);
            status.setTransaction_status("PDNG");
            ArrayList<StatusReasonInformation> statusReasonInformations = new ArrayList<>();
            for (String errorCode : errorCodes){
                statusReasonInformations.add(StatusReasonInformation.builder()
                        .reason(Reason.builder().proprietary(errorCode).description_en(Errors.valueOf(errorCode).getMsg()).build())
                        .build());
            }
            status.setStatus_reason_information(statusReasonInformations);
            statusList.add(status);
            mt199.getWire_payment_status_report().setTransaction_information_and_status(statusList);
        }
        this.paymentRequestValidator.clearErrorCodes();

        mt199.setPayment_status("AVAILABLE");
        mt199.setFraud_check_result(Fraud_check_result.builder()
                .action("ALLOW").score(0).build());
        log.info("JSON after::"+ new Gson().toJson(mt199));
        return mt199;
    }

    private Pacs002 produceResponse(WireMT103Payload wireMT103, List<String> errorCodes) {
        return preparePacs002("pacs002_sample.json");
    }


    @PostMapping(value = "/sendPaymentRequestV2", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 500, message = "Internal Server Error")})
    public Pacs002 validatePaymentV2(@Valid @RequestBody NoneISO noneISO, BindingResult bindingResult) {

        List<String> errorCodes = this.paymentRequestValidator.validate(noneISO);
        return produceResponse(noneISO, errorCodes);
    }

    private Pacs002 produceResponse(NoneISO noneISO, List<String> errorCodes) {
        String filename = "pacs002_sample.json";
        ClassLoader classLoader = new WiresDemoApplication().getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        String json = null;
        try {
            json = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pacs002 pacs002 = new Gson().fromJson(json, Pacs002.class);

        ArrayList<TransactionInformationStatus> statusList = new ArrayList<>();
        TransactionInformationStatus transactionInformationStatus = TransactionInformationStatus.builder()
                .original_group_information(OriginalGroupInformation.builder()
                        .original_message_identification("NoneISO")
                        .original_message_name_identification("pacs.008.001.08")
                        .build())
                .original_instruction_identification("NoneISO")
                .original_end_to_end_identification("NoneISO")
                .original_transaction_identification("NoneISO")
                .transaction_status("ACSP")
                .acceptance_datetime("acceptance datetime")
                .clearing_system_reference("clearing_system_reference")
                .build();
        ArrayList<StatusReasonInformation> statusReasonInformation = new ArrayList<>();
        statusReasonInformation.add(0, StatusReasonInformation.builder()
                .reason(Reason.builder().proprietary("0").build()).build());
        transactionInformationStatus.setStatus_reason_information(statusReasonInformation);
        pacs002.getFi_to_fi_payment_status_report().getTransaction_information_and_status().set(0, transactionInformationStatus);

        if(errorCodes != null && !errorCodes.isEmpty()) {
            statusList = new ArrayList<>();
            TransactionInformationStatus status = pacs002.getFi_to_fi_payment_status_report().getTransaction_information_and_status().get(0);
            status.setTransaction_status("PDNG");
            ArrayList<StatusReasonInformation> statusReasonInformations = new ArrayList<>();
            for (String errorCode : errorCodes){
                statusReasonInformations.add(StatusReasonInformation.builder()
                        .reason(Reason.builder().proprietary(errorCode).build())
                        .build());
            }
            status.setStatus_reason_information(statusReasonInformations);
            statusList.add(status);
            pacs002.getFi_to_fi_payment_status_report().setTransaction_information_and_status(statusList);
        }
        this.paymentRequestValidator.clearErrorCodes();

        pacs002.setPayment_status("AVAILABLE");
        pacs002.setFraud_check_result(Fraud_check_result.builder()
                .action("ALLOW").score(0).build());
        return pacs002;

    }

    private Pacs002 produceResponse(Pacs008 pacs008, List<String> errorCodes) {
        String filename = "pacs002_sample.json";
        ClassLoader classLoader = new WiresDemoApplication().getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        String json = null;
        try {
            json = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pacs002 pacs002 = new Gson().fromJson(json, Pacs002.class);

        GroupHeader group_header = GroupHeader.builder()
                .message_identification(pacs008.getFi_to_fi_customer_credit_transfer().getGroup_header().getMessage_identification())
                .creation_datetime(pacs008.getFi_to_fi_customer_credit_transfer().getGroup_header().getCreation_datetime())
                .instructing_agent(
                        Agent.builder()
                                .financial_institution_identification(FinancialIntitutionIdentification.builder()
                                        .clearing_system_member_identification(ClearingSystemMemberIdentification.builder()
                                                .member_identification(pacs008.getFi_to_fi_customer_credit_transfer().getGroup_header().getInstructing_agent().getFinancial_institution_identification().getClearing_system_member_identification().getMember_identification())
                                                .build())
                                        .build())
                                .build()
                )
                .instructed_agent(
                        Agent.builder()
                                .financial_institution_identification(FinancialIntitutionIdentification.builder()
                                        .clearing_system_member_identification(ClearingSystemMemberIdentification.builder()
                                                .member_identification(pacs008.getFi_to_fi_customer_credit_transfer().getGroup_header().getInstructed_agent().getFinancial_institution_identification().getClearing_system_member_identification().getMember_identification())
                                                .build())
                                        .build())
                                .build()
                ).build();
        pacs002.getFi_to_fi_payment_status_report().setGroup_header(group_header);
        ArrayList<TransactionInformationStatus> statusList = new ArrayList<>();
        TransactionInformationStatus transactionInformationStatus = TransactionInformationStatus.builder()
                .original_group_information(OriginalGroupInformation.builder()
                        .original_message_identification(pacs008.getFi_to_fi_customer_credit_transfer().getGroup_header().getMessage_identification())
                        .original_message_name_identification("pacs.008.001.08")
                        .build())
                .original_instruction_identification(pacs008.getFi_to_fi_customer_credit_transfer().getCredit_transfer_transaction_information().get(0).getPayment_identification().getInstruction_identification())
                .original_end_to_end_identification(pacs008.getFi_to_fi_customer_credit_transfer().getCredit_transfer_transaction_information().get(0).getPayment_identification().getEnd_to_end_identification())
                .original_transaction_identification(pacs008.getFi_to_fi_customer_credit_transfer().getCredit_transfer_transaction_information().get(0).getPayment_identification().getTransaction_identification())
                .transaction_status("ACSP")
                .acceptance_datetime("acceptance datetime")
                .clearing_system_reference("clearing_system_reference")
                .build();
        ArrayList<StatusReasonInformation> statusReasonInformation = new ArrayList<>();
        statusReasonInformation.add(0, StatusReasonInformation.builder()
                .reason(Reason.builder().proprietary("0").build()).build());
        transactionInformationStatus.setStatus_reason_information(statusReasonInformation);
        pacs002.getFi_to_fi_payment_status_report().getTransaction_information_and_status().set(0, transactionInformationStatus);

        if(errorCodes != null && !errorCodes.isEmpty()) {
            statusList = new ArrayList<>();
            TransactionInformationStatus status = pacs002.getFi_to_fi_payment_status_report().getTransaction_information_and_status().get(0);
            status.setTransaction_status("PDNG");
            ArrayList<StatusReasonInformation> statusReasonInformations = new ArrayList<>();
            for (String errorCode : errorCodes){
                statusReasonInformations.add(StatusReasonInformation.builder()
                        .reason(Reason.builder().proprietary(errorCode).build())
                        .build());
            }
            status.setStatus_reason_information(statusReasonInformations);
            statusList.add(status);
            pacs002.getFi_to_fi_payment_status_report().setTransaction_information_and_status(statusList);
        }
        this.paymentRequestValidator.clearErrorCodes();

        pacs002.setPayment_status("AVAILABLE");
        pacs002.setFraud_check_result(Fraud_check_result.builder()
                .action("ALLOW").score(0).build());
        return pacs002;

    }

    private Pacs002 preparePacs002(String fileName){
        ClassLoader classLoader = new WiresDemoApplication().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        String json = null;
        try {
            json = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(json, Pacs002.class);
    }

}
