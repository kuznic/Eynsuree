package com.meedra.eynsuree.dto;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentFormDto {

    private String clientToken;

    private Long premium;

    private String payerReference;

    private String organizationName;

    private String beneficiaryAccount;

    private String companyName;

    private String customerId;
}
