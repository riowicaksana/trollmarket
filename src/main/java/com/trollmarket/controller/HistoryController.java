package com.trollmarket.controller;

import com.trollmarket.dto.history.HistoryGridDTO;
import com.trollmarket.entity.Account;
import com.trollmarket.service.AccountService;
import com.trollmarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    private AccountService accountService;
    private OrderService orderService;

    @Autowired
    public HistoryController(AccountService accountService, OrderService orderService) {
        this.accountService = accountService;
        this.orderService = orderService;
    }

    @GetMapping("history-table")
    public String historyList(Model model,
                              @RequestParam(value = "buyer",required = false,defaultValue = "") String buyer,
                              @RequestParam(value = "seller",required = false,defaultValue = "") String seller){

        List<Account> sellerList = accountService.findAllSeller();
        List<Account> buyerList = accountService.findAllBuyer();


        if(buyer.equals("")&&seller.equals("")){
            List<HistoryGridDTO> historyGridDTOList = orderService.findAllOrderHistory();
            model.addAttribute("historyList",historyGridDTOList);
        }else{
            List<HistoryGridDTO> historyGridDTOList = orderService.findAllOrderHistorBuyerandSellerName(buyer,seller);
            model.addAttribute("historyList",historyGridDTOList);
        }

        model.addAttribute("sellerList",sellerList);
        model.addAttribute("buyerList",buyerList);
        return "history/history-table";
    }



}
