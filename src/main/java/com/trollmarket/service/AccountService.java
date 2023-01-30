package com.trollmarket.service;

import com.trollmarket.dto.account.AccountDetailsDTO;
import com.trollmarket.dto.account.AdminRegisterDTO;
import com.trollmarket.dto.account.BuyerRegisterDTO;
import com.trollmarket.dto.account.SellerRegisterDTO;
import com.trollmarket.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {


    void addBalance(BigDecimal addBalance, String name);

    void registerBuyer(BuyerRegisterDTO buyerRegisterDTO);

    void registerSeller(SellerRegisterDTO sellerRegisterDTO);

    AccountDetailsDTO findAccountById(String name);

    UserDetails loadUserByUsername(String username);

    void registerAdmin(AdminRegisterDTO adminRegisterDTO);

    List<Account> findAllSeller();

    List<Account> findAllBuyer();

    public String getAccountRole(String username);
}
