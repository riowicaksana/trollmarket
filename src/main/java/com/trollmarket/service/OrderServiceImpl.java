package com.trollmarket.service;


import com.trollmarket.dto.history.HistoryGridDTO;
import com.trollmarket.entity.Orders;
import com.trollmarket.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<HistoryGridDTO> findAllOrderHistory() {
        List<Orders> ordersList = orderRepository.findAll();

        List<HistoryGridDTO> historyGridDTOList = new ArrayList<>();

        for(Orders orders:ordersList){
            HistoryGridDTO historyGridDTO = new HistoryGridDTO(
                    orders.getDateHistory(),
                    orders.getProduct().getAccount().getName(),
                    orders.getAccount().getName(),
                    orders.getProduct().getName(),
                    orders.getQuantity(),
                    orders.getShipment().getName(),
                    orders.getTotalInRp()
            );
            historyGridDTOList.add(historyGridDTO);
        }

        return historyGridDTOList;
    }

    @Override
    public List<HistoryGridDTO> findAllOrderHistorBuyerandSellerName(String buyer, String seller) {
        List<Orders> ordersList = new ArrayList<>();
        List<HistoryGridDTO> gridDTOList = new ArrayList<>();

        if(!buyer.equals("")&&seller.equals("")){
          ordersList = orderRepository.findOrderByBuyer(buyer);
            System.out.println("Buyer is work"+ordersList);
        }else if(buyer.equals("")&&!seller.equals("")){
            ordersList = orderRepository.findOrderBySeller(seller);
            System.out.println("seller is work"+ordersList);
        }else {
            ordersList = orderRepository.findOrderByBuyerandSeller(buyer,seller);
            System.out.println("Seller is work and And Buyer is work");
        }

        for(Orders orders:ordersList){
            HistoryGridDTO historyGridDTO = new HistoryGridDTO(
                    orders.getDateHistory(),
                    orders.getProduct().getAccount().getName(),
                    orders.getAccount().getName(),
                    orders.getProduct().getName(),
                    orders.getQuantity(),
                    orders.getShipment().getName(),
                    orders.getTotalInRp()
            );
            gridDTOList.add(historyGridDTO);
        }
        return gridDTOList;
    }

    @Override
    public List<HistoryGridDTO> findHistoryByBuyerName(String name) {

        List<Orders> ordersList = orderRepository.findOrderByBuyer(name);
        List<HistoryGridDTO> gridDTOList = new ArrayList<>();
        for(Orders orders:ordersList){
            HistoryGridDTO historyGridDTO = new HistoryGridDTO(
                    orders.getDateHistory(),
                    orders.getProduct().getAccount().getName(),
                    orders.getAccount().getName(),
                    orders.getProduct().getName(),
                    orders.getQuantity(),
                    orders.getShipment().getName(),
                    orders.getTotalInRp()
            );
            gridDTOList.add(historyGridDTO);
        }
        return gridDTOList;
    }
}
