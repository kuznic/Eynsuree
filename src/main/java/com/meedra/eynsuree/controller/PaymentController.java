package com.meedra.eynsuree.controller;

import com.meedra.eynsuree.dto.AmountDto;
import com.meedra.eynsuree.dto.PaymentFormDto;
import com.meedra.eynsuree.dto.StitchPaymentRequestDto;
import com.meedra.eynsuree.enums.BankId;
import com.meedra.eynsuree.enums.Currency;
import com.meedra.eynsuree.implementation.*;
import com.meedra.eynsuree.stitch.service.ClientTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.meedra.eynsuree.stitch.utility.EynsureUtils.*;


@Slf4j
@Controller
public class PaymentController {


    private final InsuredItemService insuredItemService;

    private final JpaUserDetailsService jpaUserDetailsService;

    private final ClientTokenService clientTokenService;

    private final StitchClientService stitchClientService;

    private final TransactionHandlerService transactionHandlerService;


    @Autowired
    public PaymentController(InsuredItemService insuredItemService, JpaUserDetailsService jpaUserDetailsService,
                             ClientTokenService clientTokenService, StitchClientService stitchClientService,
                             TransactionHandlerService transactionHandlerService) {
        this.insuredItemService = insuredItemService;
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.clientTokenService = clientTokenService;
        this.stitchClientService = stitchClientService;
        this.transactionHandlerService = transactionHandlerService;
    }

    //renders the payment form
    @RequestMapping(value = "/payment/{id}")
    public String getPaymentForm(@PathVariable("id") UUID id, Model model) throws NoSuchFieldException {

        var insuredItem = insuredItemService.fetchInsuredItem(id);


        model.addAttribute("premium", insuredItem.getInsurancePremium());
        model.addAttribute("organizationName", insuredItem.getOrganization().getName());
        model.addAttribute("beneficiaryAccount",insuredItem.getOrganization().getBankAccountNumber());
        model.addAttribute("companyName",insuredItem.getOrganization().getName());
        model.addAttribute("customerId", insuredItem.getCustomer().getEmail());

        return "paymentform";
    }


    @RequestMapping(value = "/submitpayment")
    @PostMapping
    public String submitPayment(HttpServletRequest request, @ModelAttribute PaymentFormDto form, Model model) {


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


        var payment = clientTokenService.callStitchGraphQLService(stitchPaymentRequest);

        if (payment.length() == 0){

            return "error";
        }

        var stitchPaymentUrl =  parsePaymentResponse(payment);

        var redirectUrl = getDebitUrl(stitchPaymentUrl.getUrl(),
                stitchClientService.getCredentials().getRedirectUrls().get(3));


        //save payment

        transactionHandlerService.createTransaction(form.getOrganizationName(),
                form.getCustomerId(),
                form.getPremium(),
                form.getPayerReference(),
                stitchPaymentRequest.getExternalReference(),
                stitchPaymentUrl.getId());


        return "redirect:" + redirectUrl;
    }






}
