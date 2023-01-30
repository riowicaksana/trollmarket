package com.trollmarket.dto.shipment;

import lombok.*;

import javax.persistence.Column;
import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ShipmentGridDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String price;

    @Getter
    @Setter
    private BigDecimal priceNonConvert;


    @Getter
    @Setter
    private Boolean serviceNonConvert;

    @Getter
    @Setter
    private String service;
}
