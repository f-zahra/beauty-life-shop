package com.beautyLifeShop.ecom.controllers;

import com.beautyLifeShop.ecom.models.Product;
import com.beautyLifeShop.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;




@RestController
public class ProductController {

    @Autowired
    private ProductService productService;



    @GetMapping("api/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Optional<Product> product = productService.loadProduct(id);
        return  ResponseEntity.ok(product.get());
    }


    @GetMapping("api/products")
    public List<Product> getProducts(){
        return  productService.getProductList();
    }



    @PostMapping("api/add-new-product")
    public Product addNewProduct(@RequestBody Product product){
        return  productService.registerProduct(product);}
}
