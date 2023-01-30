package com.trollmarket.repository;


import com.trollmarket.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("""
    SELECT ord
    FROM Orders as ord
    JOIN Account as acc on ord.account = acc.username
    WHERE acc.name like %:buyer%
""")
    List<Orders> findOrderByBuyer(String buyer);

    @Query("""
    SELECT ord
    FROM Orders as ord
    JOIN Product as pro on ord.product.id = pro.id
    JOIN Account as acc on pro.account = acc.username
    WHERE acc.name like %:seller%
""")
    List<Orders> findOrderBySeller(String seller);

    @Query("""
    SELECT ord
    FROM Orders as ord
    JOIN Product as pro on ord.product.id = pro.id
    JOIN Account as sell on pro.account = sell.username
    JOIN Account as buy on ord.account.name = buy.name
    WHERE sell.name like %:seller% or buy.name like  %:buyer%
""")
    List<Orders> findOrderByBuyerandSeller(String buyer, String seller);
}
