package com.trollmarket.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "Name")
    @Getter
    @Setter
    private String name;

    @Column(name = "Price")
    @Getter
    @Setter
    private BigDecimal price;

    @Column(name = "Service")
    @Getter
    @Setter
    private Boolean service;

    @ManyToOne
    @JoinColumn(name = "AdminID")
    @Getter
    @Setter
    private Account account;

    public Shipment(String name, BigDecimal price, Boolean service, Account account) {
        this.name = name;
        this.price = price;
        this.service = service;
        this.account = account;
    }

    public String shipmentServiceConvert(){
        String statusService;
        if(this.service==true){
            statusService = "yes";
        }else {
            statusService = "no";
        }
        return statusService;
    }

    public String getBalanceInRp(){
        Locale indonesia = new Locale("ID","ID");

        String indoFormat = NumberFormat.getCurrencyInstance(indonesia).format(this.price);

        return indoFormat;
    }


}
