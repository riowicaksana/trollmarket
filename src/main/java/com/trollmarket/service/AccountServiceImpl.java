package com.trollmarket.service;

import com.trollmarket.configuration.ApplicationUserDetails;
import com.trollmarket.dto.account.AccountDetailsDTO;
import com.trollmarket.dto.account.AdminRegisterDTO;
import com.trollmarket.dto.account.BuyerRegisterDTO;
import com.trollmarket.dto.account.SellerRegisterDTO;
import com.trollmarket.entity.Account;
import com.trollmarket.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private AccountRepository accountRepository;

    private PasswordEncoder passwordEncoder;


    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void addBalance(BigDecimal addBalance, String name) {
       Account account = accountRepository.findById(name).get();

       account.setBalance(account.getBalance().add(addBalance));

       accountRepository.save(account);

    }

    @Override
    public void registerBuyer(BuyerRegisterDTO buyerRegisterDTO) {
        buyerRegisterDTO.setRole("Buyer");
        String hashPassword = passwordEncoder.encode(buyerRegisterDTO.getPassword());
        Account account = new Account(
                buyerRegisterDTO.getUsername(),
                hashPassword,
                buyerRegisterDTO.getRole(),
                buyerRegisterDTO.getName(),
                buyerRegisterDTO.getAddress());

        accountRepository.save(account);
    }

    @Override
    public void registerSeller(SellerRegisterDTO sellerRegisterDTO) {
        sellerRegisterDTO.setRole("Seller");
        String hashPassword = passwordEncoder.encode(sellerRegisterDTO.getPassword());
        Account account = new Account(
                sellerRegisterDTO.getUsername(),
                hashPassword,
                sellerRegisterDTO.getRole(),
                sellerRegisterDTO.getName(),
                sellerRegisterDTO.getAddress());
        accountRepository.save(account);
    }

    @Override
    public AccountDetailsDTO findAccountById(String name) {
       Account account =  accountRepository.findById(name).get();

      AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
      accountDetailsDTO.setUsername(account.getUsername());
      accountDetailsDTO.setRole(account.getRole());
      accountDetailsDTO.setName(account.getName());
      accountDetailsDTO.setAddress(account.getAddress());
      accountDetailsDTO.setBalance(account.getBalanceInRp());
        return accountDetailsDTO;
    }

    @Override
    public void registerAdmin(AdminRegisterDTO adminRegisterDTO) {
        String hashPassword = passwordEncoder.encode(adminRegisterDTO.getPassword());

        Account account = new Account(
                adminRegisterDTO.getUsername(),
                hashPassword,
                "Admin",
                null,
                null,
                null
        );

        accountRepository.save(account);
    }

    @Override
    public List<Account> findAllSeller() {
        List<Account> sellerList = accountRepository.findAccountByRole("Seller");
        return sellerList;
    }

    @Override
    public List<Account> findAllBuyer() {
        List<Account> buyerList = accountRepository.findAccountByRole("Buyer");
        return buyerList;
    }

    @Override
    public String getAccountRole(String username) {
        Optional<Account> nullableEntity = accountRepository.findById(username);
        Account account = nullableEntity.get();
        return account.getRole();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> nullableEntity = accountRepository.findById(username);
        Account account = nullableEntity.get();
        return new ApplicationUserDetails(account);
    }
}
