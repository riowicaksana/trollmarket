package com.trollmarket.dto.cart;

import lombok.*;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UpsertCartDTO {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Long productID;

    @Getter
    @Setter
    @Min(value = 0, message = "Quantity tidak boleh lebih kecil dari 0")
    private Integer quantity;

    @Getter
    @Setter
    private Long shipmentID;

    @Getter
    @Setter
    private BigDecimal totalPrice;

    @Getter
    @Setter
    private String buyerID;
}
