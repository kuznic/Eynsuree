package com.meedra.eynsuree.dto;


import com.meedra.eynsuree.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AmountDto {

    private String quantity;
    private Currency currency;
}
