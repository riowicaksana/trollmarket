package com.trollmarket.service;

import com.trollmarket.dto.history.HistoryGridDTO;

import java.util.List;

public interface OrderService {
    List<HistoryGridDTO> findAllOrderHistory();

    List<HistoryGridDTO> findAllOrderHistorBuyerandSellerName(String buyer, String seller);

    List<HistoryGridDTO> findHistoryByBuyerName(String name);
}
