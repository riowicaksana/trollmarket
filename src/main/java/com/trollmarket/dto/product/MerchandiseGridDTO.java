package com.trollmarket.dto.product;


import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MerchandiseGridDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String category;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String price;




    @Getter
    @Setter
    private String discontinue;

}
