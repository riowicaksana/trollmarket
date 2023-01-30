package com.trollmarket.repository;

import com.trollmarket.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,String> {

    List<Account> findAccountByRole(String role);
}
