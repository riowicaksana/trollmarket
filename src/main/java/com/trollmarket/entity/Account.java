package com.trollmarket.entity;

import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column(name = "Username")
    @Getter
    @Setter
    private String username;


    @Column(name = "Password")
    @Getter
    @Setter
    private String password;

    @Column(name = "Role")
    @Getter
    @Setter
    private String role;

    @Column(name = "Name")
    @Getter
    @Setter
    private String name;

    @Column(name = "Address")
    @Getter
    @Setter
    private String address;

    @Column(name = "Balance")
    @Getter
    @Setter
    private BigDecimal balance;

    public Account(String username, String password, String role, String name, String address) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.address = address;
    }

    public String getBalanceInRp(){
        Locale indonesia = new Locale("ID","ID");

        String indoFormat = NumberFormat.getCurrencyInstance(indonesia).format(this.balance);

        return indoFormat;
    }
}
