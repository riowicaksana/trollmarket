package com.trollmarket.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Id")
    @Getter
    @Setter
    private Long id;


    @Column(name = "Quantity")
    @Getter
    @Setter
    private Integer quantity;

    @Column(name = "TotalPrice")
    @Getter
    @Setter
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    @Getter
    @Setter
    private Product product;


    @ManyToOne
    @JoinColumn(name = "ShipmentID")
    @Getter
    @Setter
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "BuyerID")
    @Getter
    @Setter
    private Account account;

    public String getBalanceInRp(){
        Locale indonesia = new Locale("ID","ID");

        String indoFormat = NumberFormat.getCurrencyInstance(indonesia).format(this.totalPrice);

        return indoFormat;
    }

}
