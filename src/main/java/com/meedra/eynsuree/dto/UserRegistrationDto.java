package com.meedra.eynsuree.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
