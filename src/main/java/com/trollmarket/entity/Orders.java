package com.trollmarket.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Orders")
public class Orders{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "Date")
    @Getter
    @Setter
    private LocalDateTime date;

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

    public Orders(LocalDateTime date, Integer quantity, BigDecimal totalPrice, Product product, Shipment shipment, Account account) {
        this.date = date;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.product = product;
        this.shipment = shipment;
        this.account = account;
    }

    public String getTotalInRp(){
        Locale indonesia = new Locale("ID","ID");

        String indoFormat = NumberFormat.getCurrencyInstance(indonesia).format(this.totalPrice);

        return indoFormat;
    }

    public String getDateHistory(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(this.date);
    }
}
