package com.trollmarket.controller;

import com.trollmarket.dto.account.AdminRegisterDTO;
import com.trollmarket.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AccountService accountService;

    @Autowired
    public AdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/register-admin")
    public String registerAdmin(Model model) {
        AdminRegisterDTO adminRegisterDTO = new AdminRegisterDTO();
        model.addAttribute("admin", adminRegisterDTO);
        return "account/admin-register-form";
    }

    @PostMapping("/registerAdmin")
    public String saveAdmin(@ModelAttribute("admin") AdminRegisterDTO adminRegisterDTO) {
        accountService.registerAdmin(adminRegisterDTO);
        return "redirect:/account/admin-register-form";
    }

}
