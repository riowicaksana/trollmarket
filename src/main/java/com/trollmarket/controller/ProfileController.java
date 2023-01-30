package com.trollmarket.controller;

import com.trollmarket.dto.account.AccountDetailsDTO;
import com.trollmarket.dto.history.HistoryGridDTO;
import com.trollmarket.repository.AccountRepository;
import com.trollmarket.service.AccountService;
import com.trollmarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private AccountService accountService;

    private OrderService orderService;
    private final AccountRepository accountRepository;

    @Autowired
    public ProfileController(AccountService accountService, OrderService orderService,
                             AccountRepository accountRepository) {
        this.accountService = accountService;
        this.orderService = orderService;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/profile-detail")
    public String profileDetail( Model model, Principal principal){



        String name = principal.getName();
        AccountDetailsDTO accountDetails = accountService.findAccountById(name);

        List<HistoryGridDTO> historyGridDTOList = orderService.findHistoryByBuyerName(name);

        model.addAttribute("historyList",historyGridDTOList);
        model.addAttribute("accountDetail",accountDetails);
        model.addAttribute("name",name);
        return "profile/detail-profile";
    }

    //test 123
    @GetMapping("/profile-detail-popup")
    public String profileDetailPopUp(Model model,Principal principal){
        String name = principal.getName();
        AccountDetailsDTO accountDetails = accountService.findAccountById(name);

        List<HistoryGridDTO> historyGridDTOList = orderService.findHistoryByBuyerName(name);

        model.addAttribute("historyList",historyGridDTOList);
        model.addAttribute("accountDetail",accountDetails);
        model.addAttribute("name",name);
        return "profile/detail-profile";
    }

    @PostMapping("add-balance")
    public String addBalance(@Valid @ModelAttribute("accountDetail") AccountDetailsDTO accountDetailsDTO,BindingResult bindingResult,
                             Principal principal,
                             RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getErrorCount());
            redirectAttributes.addFlashAttribute("minError",bindingResult.getFieldError().getDefaultMessage());
            return "redirect:/profile/profile-detail-popup";
        }
        accountService.addBalance(accountDetailsDTO.getAddBalance(),principal.getName());
        return "redirect:/profile/profile-detail";
    }
}
