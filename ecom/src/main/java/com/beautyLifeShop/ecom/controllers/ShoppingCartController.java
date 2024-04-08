package com.beautyLifeShop.ecom.controllers;

import com.beautyLifeShop.ecom.models.ShoppingCart;
import com.beautyLifeShop.ecom.service.ShoppingCartService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



//@CrossOrigin("http://localhost:4200/")
@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    @PostMapping("api/cart/add")
    public ResponseEntity<String> addToCart(@RequestParam Long productId,HttpSession session) {

         ShoppingCart cart = shoppingCartService.getCart(session);

        // Add the product to the shopping cart
        shoppingCartService.addItemToCart(cart, productId, session);
       // shoppingCartService.updateShoppingCart(session, cart);
        return ResponseEntity.ok("Product added to cart successfully");
    }




    @GetMapping("api/cart")
    public ResponseEntity<ShoppingCart> getShoppingCart( HttpSession session) {

       return  ResponseEntity.ok(shoppingCartService.getCart(session));
    }

}
