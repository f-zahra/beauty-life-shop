package com.beautyLifeShop.ecom.controllers;

import com.beautyLifeShop.ecom.models.CartItem;
import com.beautyLifeShop.ecom.models.ShoppingCart;
import com.beautyLifeShop.ecom.service.ShoppingCartService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



//@CrossOrigin("http://localhost:4200/")
@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;



    @PostMapping("api/cart/add")
    public ResponseEntity<String> addToCart(@RequestParam Long productId,HttpServletRequest request) {


        // Retrieve the JSESSIONID from the HttpSession
        HttpSession session  = request.getSession();
         ShoppingCart cart = shoppingCartService.getCart(session);

        // Add the product to the shopping cart
        shoppingCartService.addItemToCart(cart, productId, session);
      // shoppingCartService.updateShoppingCart(session, cart);
        return ResponseEntity.ok().body("{\"message\": \"Product added to cart successfully\"}");

    }


    @PostMapping("api/cart/remove")
    public ResponseEntity<String> removeFromCart(@RequestParam Long productId,HttpServletRequest request) {


        // Retrieve the JSESSIONID from the HttpSession
        HttpSession session  = request.getSession();
        ShoppingCart cart = shoppingCartService.getCart(session);

        // Add the product to the shopping cart
        shoppingCartService.removeItem(cart, productId, session);
        // shoppingCartService.updateShoppingCart(session, cart);
        return ResponseEntity.ok().body("{\"message\": \"Product removed from cart successfully\"}");

    }

    @PostMapping("api/cart/removeItem")
    public  ResponseEntity<String> removeItem(@RequestParam String cartId,HttpServletRequest request){
        // Retrieve the JSESSIONID from the HttpSession
        HttpSession session  = request.getSession();
        ShoppingCart cart = shoppingCartService.getCart(session);

        // Add the product to the shopping cart
        shoppingCartService.removeItemFromCart(cart, cartId, session);
        // shoppingCartService.updateShoppingCart(session, cart);
        return ResponseEntity.ok().body("{\"message\": \"Product removed successfully\"}");
    }




    @GetMapping("api/cart")
    public ResponseEntity<ShoppingCart> getShoppingCart(HttpServletRequest request) {

        HttpSession session  = request.getSession();
       return  ResponseEntity.ok(shoppingCartService.getCart(session));
    }

    @GetMapping("api/cart/cartItems")
    public ResponseEntity<List<CartItem>> getCartItems(HttpServletRequest request) {

        HttpSession session  = request.getSession();
        return  ResponseEntity.ok(shoppingCartService.getCart(session).getCartItems());
    }

}
