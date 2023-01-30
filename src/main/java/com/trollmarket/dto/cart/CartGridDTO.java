package com.trollmarket.dto.cart;

import lombok.*;
import lombok.extern.java.Log;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CartGridDTO {

    @Getter
    @Setter
    private Long Id;

    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    private BigDecimal quantity;

    @Getter
    @Setter
    private String shipmentName;

    @Getter
    @Setter
    private String sellerName;

    @Getter
    @Setter
    private String totalPrice;

}
