package com.trollmarket.service;

import com.trollmarket.dto.product.MerchandiseGridDTO;
import com.trollmarket.dto.product.UpsertProductDTO;

import java.util.List;

public interface ProductService {
    List<MerchandiseGridDTO> findProductById(String name);

    void saveProduct(UpsertProductDTO upsertProductDTO,String name);

    UpsertProductDTO findProductByOneId(Long id);

    void deleteById(Long id);

    void setDiscontinueToTrue(Long Id);

    List<UpsertProductDTO> findAllProduct();

    List<UpsertProductDTO> findProductByNameCategoryDescription(String name, String category, String description);
}
