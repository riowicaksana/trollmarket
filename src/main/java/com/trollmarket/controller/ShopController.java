package com.trollmarket.controller;

import com.trollmarket.dto.cart.UpsertCartDTO;
import com.trollmarket.dto.product.MerchandiseGridDTO;
import com.trollmarket.dto.product.UpsertProductDTO;
import com.trollmarket.dto.shipment.ShipmentGridDTO;
import com.trollmarket.service.CartService;
import com.trollmarket.service.ProductService;
import com.trollmarket.service.ShipmentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private ProductService productService;

    private CartService cartService;

    private ShipmentService shipmentService;

    @Autowired
    public ShopController(ProductService productService, CartService cartService, ShipmentService shipmentService) {
        this.productService = productService;
        this.cartService = cartService;
        this.shipmentService = shipmentService;
    }







    @GetMapping("/shop-table")
    public String shopTable(Model model,
                            @RequestParam(value = "name",required = false,defaultValue = "") String name,
                            @RequestParam(value = "category",required = false,defaultValue = "") String category,
                            @RequestParam(value = "description",required = false,defaultValue = "") String description){

        List<UpsertProductDTO> upsertProductDTOList;
        UpsertCartDTO upsertCartDTO = new UpsertCartDTO();
        List<ShipmentGridDTO> shipmentList = shipmentService.findAll();
        if(name.equals("")&&category.equals("")&&description.equals("")){
           upsertProductDTOList = productService.findAllProduct();
           model.addAttribute("products",upsertProductDTOList);
        }else{
            upsertProductDTOList = productService.findProductByNameCategoryDescription(name,category,description);
            model.addAttribute("products",upsertProductDTOList);
        }
        model.addAttribute("shipmentList",shipmentList);
        model.addAttribute("cart",upsertCartDTO);

        return "shop/shop-table";
    }


    @GetMapping("/shop-table-popup")
    public String shopTablePopUp(Model model,
                            @RequestParam(value = "name",required = false,defaultValue = "") String name,
                            @RequestParam(value = "category",required = false,defaultValue = "") String category,
                            @RequestParam(value = "description",required = false,defaultValue = "") String description){

        List<UpsertProductDTO> upsertProductDTOList;
        UpsertCartDTO upsertCartDTO = new UpsertCartDTO();
        List<ShipmentGridDTO> shipmentList = shipmentService.findAll();
        if(name.equals("")&&category.equals("")&&description.equals("")){
            upsertProductDTOList = productService.findAllProduct();
            model.addAttribute("products",upsertProductDTOList);
        }else{
            upsertProductDTOList = productService.findProductByNameCategoryDescription(name,category,description);
            model.addAttribute("products",upsertProductDTOList);
        }
        model.addAttribute("shipmentList",shipmentList);
        model.addAttribute("cart",upsertCartDTO);

        return "shop/shop-table";
    }



    @PostMapping("/save-cart")
    public String saveCart(@Valid @ModelAttribute("cart") UpsertCartDTO upsertCartDTO,
                           BindingResult bindingResult,
                           Principal principal,
                           RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getErrorCount());
            redirectAttributes.addFlashAttribute("minError",bindingResult.getFieldError().getDefaultMessage());
            return "redirect:/shop/shop-table-popup";
        }

        upsertCartDTO.setBuyerID(principal.getName());
        cartService.save(upsertCartDTO);

        return "redirect:/shop/shop-table";

    }




}
