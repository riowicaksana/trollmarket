package com.trollmarket.dto.product;


import lombok.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UpsertProductDTO {

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
    private BigDecimal price;

    @Getter
    @Setter
    private Boolean discontinue;

    public String getBalanceInRp(){
        Locale indonesia = new Locale("ID","ID");

        String indoFormat = NumberFormat.getCurrencyInstance(indonesia).format(this.price);

        return indoFormat;
    }
}
