package com.meedra.eynsuree.stitch.utility;


import lombok.Getter;

@Getter
public class Mutations {

    private final String createPaymentRequestMutation = "mutation CreatePaymentRequest(\n" +
            "    $amount: MoneyInput!,\n" +
            "    $payerReference: String!,\n" +
            "    $beneficiaryReference: String!,\n" +
            "    $externalReference: String,\n" +
            "    $beneficiaryName: String!,\n" +
            "    $beneficiaryBankId: BankBeneficiaryBankId!,\n" +
            "    $beneficiaryAccountNumber: String!) {\n" +
            "  clientPaymentInitiationRequestCreate(input: {\n" +
            "      amount: $amount,\n" +
            "      payerReference: $payerReference,\n" +
            "      beneficiaryReference: $beneficiaryReference,\n" +
            "      externalReference: $externalReference,\n" +
            "      beneficiary: {\n" +
            "          bankAccount: {\n" +
            "              name: $beneficiaryName,\n" +
            "              bankId: $beneficiaryBankId,\n" +
            "              accountNumber: $beneficiaryAccountNumber\n" +
            "          }\n" +
            "      }\n" +
            "    }) {\n" +
            "    paymentInitiationRequest {\n" +
            "      id\n" +
            "      url\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
