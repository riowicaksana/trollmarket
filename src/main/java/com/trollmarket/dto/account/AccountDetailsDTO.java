package com.trollmarket.dto.account;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountDetailsDTO {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String role;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String balance;


    @Min(value = 0, message = "Nominal tidak boleh lebih kecil dari 0")
    @Getter
    @Setter
    private BigDecimal addBalance;




}
