package com.meedra.eynsuree.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meedra.eynsuree.dto.AmountDto;
import com.meedra.eynsuree.dto.PaymentDto;
import com.meedra.eynsuree.dto.StitchPaymentRequestDto;
import com.meedra.eynsuree.enums.BankId;
import com.meedra.eynsuree.enums.Currency;
import com.meedra.eynsuree.implementation.*;
import com.meedra.eynsuree.stitch.service.ClientTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.meedra.eynsuree.stitch.utility.RandomStringsGenerator.generateRandomStrings;


@Slf4j
@Controller
public class PaymentController {


    @Autowired
    private InsuredItemService insuredItemService;

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private ClientTokenService clientTokenService;


    private static final ObjectMapper objectMapper = new ObjectMapper();





    @RequestMapping(value = "/payment/{id}")
    public String getPaymentForm(@PathVariable("id") UUID id, Model model) throws NoSuchFieldException {

        var insuredItem = insuredItemService.fetchInsuredItem(id);


        model.addAttribute("premium", insuredItem.getInsurancePremium());
        model.addAttribute("organizationName", insuredItem.getOrganization().getName());
        model.addAttribute("beneficiaryAccount",insuredItem.getOrganization().getBankAccountNumber());
        model.addAttribute("companyName",insuredItem.getOrganization().getName());

        return "paymentform";
    }


    @RequestMapping(value = "/submitpayment")
    @PostMapping
    public String submitPayment(@ModelAttribute PaymentDto form, Model model) throws  IOException, URISyntaxException {


        model.getAttribute("clientToken");

        var amount =  AmountDto.builder()
                .quantity(form.getPremium().toString())
                .currency(Currency.ZAR)
                .build();




        var stitchPaymentRequest = StitchPaymentRequestDto
                .builder()
                .amount(amount)
                .beneficiaryAccountNumber(form.getBeneficiaryAccount())
                .beneficiaryBankId(BankId.fnb)
                .beneficiaryName(form.getOrganizationName())
                .beneficiaryReference(generateRandomStrings() + LocalDateTime.now().getSecond())
                .externalReference(generateRandomStrings() + LocalDateTime.now().getSecond())
                .payerReference(form.getPayerReference())
                .build();


        var httpResponse = clientTokenService.callGraphQLService(stitchPaymentRequest);

        if (httpResponse.size() == 0){

            return "errorpage";
        }

        log.info("got here");




        return "/";
    }



}
