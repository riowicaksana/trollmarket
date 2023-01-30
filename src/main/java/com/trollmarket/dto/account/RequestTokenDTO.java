package com.trollmarket.dto.account;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class RequestTokenDTO {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String secretKey;

    @Getter
    @Setter
    private String audience;
}
