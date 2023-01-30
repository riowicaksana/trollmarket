package com.trollmarket.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Product")
public class Product {
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

    @Column(name = "Category")
    @Getter
    @Setter
    private String category;

    @Column(name = "Description")
    @Getter
    @Setter
    private String description;

    @Column(name = "Price")
    @Getter
    @Setter
    private BigDecimal price;

    @Column(name = "Discontinued")
    @Getter
    @Setter
    private Boolean discontinued;

    @ManyToOne
    @JoinColumn(name = "SellerID")
    @Getter
    @Setter
    private Account account;

    public Product(String name, String category, String description, BigDecimal price, Boolean discontinued, Account account) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.discontinued = discontinued;
        this.account = account;
    }

    public String discontinueConvert(){
        String discontinue;

        if(this.discontinued==false){
            discontinue = "no";
        }else{
            discontinue = "yes";
        }
        return discontinue;
    }

    public String getBalanceInRp(){
        Locale indonesia = new Locale("ID","ID");

        String indoFormat = NumberFormat.getCurrencyInstance(indonesia).format(this.price);

        return indoFormat;
    }
}
