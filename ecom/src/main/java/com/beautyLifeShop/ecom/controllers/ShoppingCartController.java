package com.beautyLifeShop.ecom.controllers;

import com.beautyLifeShop.ecom.models.CartItem;
import com.beautyLifeShop.ecom.models.Product;
import com.beautyLifeShop.ecom.models.ShoppingCart;
import com.beautyLifeShop.ecom.service.CartNotFound;
import com.beautyLifeShop.ecom.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @PostMapping("api/add")
    public ResponseEntity<String> addToCart(@RequestParam Long productId) {
        // Get the current user's shopping cart (assuming you have user authentication)
         Optional<ShoppingCart> cart = shoppingCartService.getCurrentUserShoppingCart();

        ShoppingCart shoppingCart = cart.get();
        // Add the product to the shopping cart
        shoppingCartService.addItemToCart(shoppingCart, productId);

        return ResponseEntity.ok("Product added to cart successfully");
    }



    @GetMapping("api/cart")
    public ResponseEntity<Object> getShoppingCart() throws CartNotFound {

        Optional<ShoppingCart> shoppingCart =  shoppingCartService.getCurrentUserShoppingCart();
        if(shoppingCart.get().isEmpty()) {return  ResponseEntity.status(HttpStatus.OK).body("cart is empty");}
        else return  ResponseEntity.ok(shoppingCart.get());
    }




}