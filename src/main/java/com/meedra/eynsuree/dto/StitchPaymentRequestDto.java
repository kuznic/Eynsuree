package com.meedra.eynsuree.dto;

import com.meedra.eynsuree.enums.BankId;
import lombok.*;
import org.springframework.stereotype.Component;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StitchPaymentRequestDto {

    private String externalReference;

    private String beneficiaryReference;

    private String payerReference;

    private String beneficiaryName;

    private String beneficiaryAccountNumber;

    private AmountDto amount;

    private BankId beneficiaryBankId;

}
