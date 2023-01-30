package com.trollmarket.dto.account;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ResponseTokenDTO {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String role;

    @Getter
    @Setter
    private String token;
}
