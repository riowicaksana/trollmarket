package com.trollmarket.service;

import com.trollmarket.dto.cart.CartGridDTO;
import com.trollmarket.dto.cart.UpsertCartDTO;
import com.trollmarket.entity.Account;
import com.trollmarket.entity.Cart;
import com.trollmarket.entity.Orders;
import com.trollmarket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    private AccountRepository accountRepository;

    private ProductRepository productRepository;

    private ShipmentRepository shipmentRepository;
    private  CartRepository cartRepository;

    private OrderRepository orderRepository;

    @Autowired
    public CartServiceImpl(AccountRepository accountRepository, ProductRepository productRepository, ShipmentRepository shipmentRepository, CartRepository cartRepository, OrderRepository orderRepository) {
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
        this.shipmentRepository = shipmentRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    public void save(UpsertCartDTO upsertCartDTO) {

        BigDecimal totalPriceItem = productRepository.findById(upsertCartDTO.getProductID()).get().getPrice()
                .multiply(new BigDecimal(upsertCartDTO.getQuantity()));

        BigDecimal totalPrice = totalPriceItem.add(shipmentRepository.findById(upsertCartDTO.getShipmentID()).get().getPrice());

        Cart cart = new Cart();
        cart.setProduct(productRepository.findById(upsertCartDTO.getProductID()).get());
        cart.setQuantity(upsertCartDTO.getQuantity());
        cart.setShipment(shipmentRepository.findById(upsertCartDTO.getShipmentID()).get());
        cart.setTotalPrice(totalPrice);
        cart.setAccount(accountRepository.findById(upsertCartDTO.getBuyerID()).get());

        cartRepository.save(cart);

    }

    @Override
    public List<CartGridDTO> findCartByUsername(String name) {
        List<Cart> cartList = cartRepository.findCartByName(name);

        List<CartGridDTO> cartGridDTOS = new ArrayList<>();

        for(Cart cart:cartList){
            CartGridDTO cartGridDTO = new CartGridDTO(
                    cart.getId(),
                    cart.getProduct().getName(),
                   new BigDecimal( cart.getQuantity()) ,
                    cart.getShipment().getName(),
                    cart.getProduct().getAccount().getName(),
                    cart.getBalanceInRp()
            );
            cartGridDTOS.add(cartGridDTO);
        }

        return cartGridDTOS;
    }

    @Override
    public void deleteCart(Long Id) {
        cartRepository.deleteById(Id);
    }

    @Override
    public void pruchaseCart(String name) {
        List<Cart> cartList = cartRepository.findCartByName(name);
//        List<Order> orderList = new ArrayList<>();
        Account account = accountRepository.findById(name).get();
        System.out.println(cartList);
        for(Cart cart:cartList){
            System.out.println(cart.getId());
            Orders order = new Orders(
                    LocalDateTime.now(),
                    cart.getQuantity(),
                    cart.getTotalPrice(),
                    cart.getProduct(),
                    cart.getShipment(),
                    cart.getAccount()
            );
            account.setBalance(account.getBalance().subtract(cart.getTotalPrice()));
//            orderList.add(order);
//            order.setDate(LocalDateTime.now());
//            order.setProduct(cart.getProduct());
//            order.setQuantity(cart.getQuantity());
//            order.setShipment(cart.getShipment());
//            order.setTotalPrice(cart.getTotalPrice());
//            order.setAccount(cart.getAccount());
            accountRepository.save(account);
            orderRepository.save(order);
            cartRepository.deleteById(cart.getId());
        }
//        orderRepository.saveAll(orderList);


    }
}
