package com.trollmarket.controller;

import com.trollmarket.dto.account.AdminRegisterDTO;
import com.trollmarket.dto.account.BuyerRegisterDTO;
import com.trollmarket.dto.account.SellerRegisterDTO;
import com.trollmarket.dto.utility.Dropdown;
import com.trollmarket.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        List<Dropdown> roleList = Dropdown.getListRole();
        model.addAttribute("roleList",roleList);
        return "account/login-form";
    }

    @GetMapping("/register-seller")
    public String registerSeller(Model model){
        SellerRegisterDTO sellerRegisterDTO = new SellerRegisterDTO();
        model.addAttribute("seller",sellerRegisterDTO);
        return "account/seller-register-form";
    }

    @GetMapping("/register-buyer")
    public String registerBuyer(Model model){
        BuyerRegisterDTO buyerRegisterDTO = new BuyerRegisterDTO();
        model.addAttribute("buyer",buyerRegisterDTO);
        return "account/buyer-register-form";
    }



    @PostMapping("/registerSeller")
    public String registerSeller(@Valid @ModelAttribute("seller") SellerRegisterDTO sellerRegisterDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "account/seller-register-form";
        }

        if (!sellerRegisterDTO.getPassword().equals(sellerRegisterDTO.getPasswordConfirmation())){
            System.out.println("Password not same");
        }
        accountService.registerSeller(sellerRegisterDTO);
        return "redirect:/account/login";
    }

    @PostMapping("/registerBuyer")
    public String registerBuyer(@Valid @ModelAttribute("buyer") BuyerRegisterDTO buyerRegisterDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "account/buyer-register-form";
        }
        accountService.registerBuyer(buyerRegisterDTO);
        return "redirect:/account/login";
    }




}
