package com.trollmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String homePage(Principal principal, Model model){
        model.addAttribute("name",principal.getName());
        return "home";
    }

}
