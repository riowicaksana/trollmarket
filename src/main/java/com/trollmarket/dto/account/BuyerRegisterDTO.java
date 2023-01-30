package com.trollmarket.dto.account;

import com.trollmarket.validation.Compare;
import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Compare(message="Password is not matched.", firstField="password", secondField="passwordConfirmation")
public class BuyerRegisterDTO {

    @Getter
    @Setter
    @NotBlank(message = "Username Tidak boleh kosong")
    private String username;

    @Getter
    @Setter
    @NotBlank(message = "Password Tidak boleh kosong")
    private String password;

    @Getter
    @Setter
    @NotBlank(message = "Password Confirmation Tidak boleh kosong")
    private String passwordConfirmation;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String role;

}
