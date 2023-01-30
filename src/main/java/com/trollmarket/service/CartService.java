package com.trollmarket.service;

import com.trollmarket.dto.cart.CartGridDTO;
import com.trollmarket.dto.cart.UpsertCartDTO;

import java.util.List;

public interface CartService {
    void save(UpsertCartDTO upsertCartDTO);

    List<CartGridDTO> findCartByUsername(String name);

    void deleteCart(Long Id);

    void pruchaseCart(String name);
}
