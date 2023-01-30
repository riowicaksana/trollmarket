package com.trollmarket.controller;

import com.trollmarket.dto.cart.CartGridDTO;
import com.trollmarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart-table")
    public String cartTable(Model model, Principal principal) {
        String name = principal.getName();

        List<CartGridDTO> cartGridDTO = cartService.findCartByUsername(name);
        model.addAttribute("cartList", cartGridDTO);
        model.addAttribute("name", name);
        return "cart/cart-table";
    }

    @GetMapping("/delete-cart/{Id}")
    public String deleteCart(@PathVariable Long Id) {
        cartService.deleteCart(Id);
        return "redirect:/cart/cart-table";
    }

    @GetMapping("/purchase")
    public String purchaseAll(Principal principal) {
        String name = principal.getName();

        cartService.pruchaseCart(name);

        return "redirect:/cart/cart-table";
    }

}
