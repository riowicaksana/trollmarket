package com.trollmarket.controller;

import com.trollmarket.dto.product.MerchandiseGridDTO;
import com.trollmarket.dto.product.UpsertProductDTO;
import com.trollmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/merchandise")
    public String merchandise(Model model, Principal principal){
        String name = principal.getName();
        List<MerchandiseGridDTO> merchandiseGridDTOList = productService.findProductById(name);
        model.addAttribute("products",merchandiseGridDTOList);
        return "product/merchandise";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model){
        UpsertProductDTO upsertProductDTO = new UpsertProductDTO();

        model.addAttribute("product",upsertProductDTO);
        return "product/product-form";
    }

    @GetMapping("update/{id}")
    public String updateProduct(@PathVariable Long id, Model model){
        UpsertProductDTO upsertProductDTO = productService.findProductByOneId(id);

        model.addAttribute("product",upsertProductDTO);
        return "product/product-form";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("product") UpsertProductDTO upsertProductDTO,Principal principal){

        String name = principal.getName();
        productService.saveProduct(upsertProductDTO,name);

        return "redirect:/product/merchandise";
    }

    @GetMapping("delete/{Id}")
    public String deleteProduct(@PathVariable Long Id){
        productService.deleteById(Id);

        return "redirect:/product/merchandise";
    }

    @GetMapping("discontinue/{Id}")
    public String discontinueProduct(@PathVariable Long Id){
        productService.setDiscontinueToTrue(Id);

        return "redirect:/product/merchandise";
    }

}
