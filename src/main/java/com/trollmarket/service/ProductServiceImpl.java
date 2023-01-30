package com.trollmarket.service;

import com.trollmarket.dto.product.MerchandiseGridDTO;
import com.trollmarket.dto.product.UpsertProductDTO;
import com.trollmarket.entity.Account;
import com.trollmarket.entity.Product;
import com.trollmarket.repository.AccountRepository;
import com.trollmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private AccountRepository accountRepository;
    private ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(AccountRepository accountRepository, ProductRepository productRepository) {
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<MerchandiseGridDTO> findProductById(String name) {
        List<Product> products = productRepository.findProductBySellerID(name);
        List<MerchandiseGridDTO > merchandiseGridDTOList = new ArrayList<>();

        for(Product product:products){
            MerchandiseGridDTO merchandiseGridDTO = new MerchandiseGridDTO(
                    product.getId(),
                    product.getName(),
                    product.getCategory(),
                    product.getDescription(),
                    product.getBalanceInRp(),
                    product.discontinueConvert()
            );
            merchandiseGridDTOList.add(merchandiseGridDTO);
        }

        return merchandiseGridDTOList;
    }

    @Override
    public void saveProduct(UpsertProductDTO upsertProductDTO,String name) {
        Account account =accountRepository.findById(name).get();
        Product product = new Product(
                upsertProductDTO.getId(),
                upsertProductDTO.getName(),
                upsertProductDTO.getCategory(),
                upsertProductDTO.getDescription(),
                upsertProductDTO.getPrice(),
                upsertProductDTO.getDiscontinue(),
                account);

        productRepository.save(product);
    }

    @Override
    public UpsertProductDTO findProductByOneId(Long id) {
        Product product = productRepository.findById(id).get();

        UpsertProductDTO upsertProductDTO = new UpsertProductDTO(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                product.getDiscontinued()
        );

        return upsertProductDTO;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void setDiscontinueToTrue(Long Id) {
      Product product =  productRepository.findById(Id).get();

      product.setDiscontinued(true);

      productRepository.save(product);

    }

    @Override
    public List<UpsertProductDTO> findAllProduct() {
      List<Product> products =  productRepository.findAll();
        List<UpsertProductDTO> upsertProductDTOList = new ArrayList<>();

      for(Product product: products){
          UpsertProductDTO upsertProductDTO = new UpsertProductDTO(
                  product.getId(),
                  product.getName(),
                  product.getCategory(),
                  product.getDescription(),
                  product.getPrice(),
                  product.getDiscontinued()
          );

          upsertProductDTOList.add(upsertProductDTO);
      }

        return upsertProductDTOList;
    }

    @Override
    public List<UpsertProductDTO> findProductByNameCategoryDescription(String name, String category, String description) {

        List<Product> products;
        List<UpsertProductDTO> upsertProductDTOList = new ArrayList<>();

        if(!name.equals("")&&category.equals("")&&description.equals("")){
            products = productRepository.findByName(name);
        }else if(name.equals("")&&!category.equals("")&&description.equals("")){
            products = productRepository.findByCategory(category);
        }else if(name.equals("")&&category.equals("")&&!description.equals("")){
            products = productRepository.findByDescription(description);
        }else{
            products = productRepository.findByNameCategoryDesc(name,category,description);
        }



        for(Product product: products){
            UpsertProductDTO upsertProductDTO = new UpsertProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getCategory(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getDiscontinued()
            );

            upsertProductDTOList.add(upsertProductDTO);
        }


        return upsertProductDTOList;
    }
}
