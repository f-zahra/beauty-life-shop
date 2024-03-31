package com.beautyLifeShop.ecom.service;

import com.beautyLifeShop.ecom.models.Product;
import com.beautyLifeShop.ecom.repository.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Optional<Product> loadProduct(Long id){
        return productRepository.findById(id);
    }

    public List<Product> getProductList(){
        return productRepository.findAll();
    }

    public Product registerProduct(Product product) {return productRepository.save(product);}
}
