package com.trollmarket.dto.shipment;

import com.trollmarket.entity.Account;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UpsertShipmentDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;


    @Getter
    @Setter
    private BigDecimal price;


    @Getter
    @Setter
    private Boolean service;

    @Getter
    @Setter
    private String adminId;

}
