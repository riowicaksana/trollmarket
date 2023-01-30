package com.trollmarket.repository;

import com.trollmarket.dto.product.UpsertProductDTO;
import com.trollmarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT pro
            FROM Product as pro
            JOIN Account as acc on pro.account = acc.username
            WHERE acc.name like %:name%
            """)
    List<Product> findProductBySellerID(String name);


    @Query("""
                SELECT pro
                FROM Product as pro
                WHERE pro.name like %:name OR pro.category like %:category OR pro.description like %:description
            """)
    List<Product> findByNameCategoryDesc(String name, String category, String description);

    @Query("""
                SELECT pro
                FROM Product as pro
                WHERE pro.name like %:name%
            """)
    List<Product> findByName(String name);
    @Query("""
                SELECT pro
                FROM Product as pro
                WHERE pro.category like %:category%
            """)
    List<Product> findByCategory(String category);
    @Query("""
                SELECT pro
                FROM Product as pro
                WHERE pro.description like %:description%
            """)
    List<Product> findByDescription(String description);
}
