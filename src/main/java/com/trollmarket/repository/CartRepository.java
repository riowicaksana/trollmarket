package com.trollmarket.repository;

import com.trollmarket.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {


    @Query(
            """
                        SELECT cart
                        FROM Cart as cart
                        JOIN Account as ac on cart.account = ac.username
                        WHERE ac.username = :name
                    """
    )
    List<Cart> findCartByName(String name);

}
