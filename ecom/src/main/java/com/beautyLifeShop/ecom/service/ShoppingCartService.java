package com.beautyLifeShop.ecom.service;

import com.beautyLifeShop.ecom.models.CartItem;
import com.beautyLifeShop.ecom.models.Product;
import com.beautyLifeShop.ecom.models.ShoppingCart;
import com.beautyLifeShop.ecom.repository.ProductRepository;
import com.beautyLifeShop.ecom.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;


    public Optional<ShoppingCart> getShoppingCart(){
        Long userId = userService.getUser().get().getId();
       return  shoppingCartRepository.findByUserId(userId);
    }





    public void addItemToCart(ShoppingCart shoppingCart, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));

        // Create a new CartItem and associate it with the shopping cart
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setShoppingCart(shoppingCart);

        // Add the cart item to the shopping cart
        shoppingCart.getCartItems().add(cartItem);

        // Update the shopping cart in the database
        shoppingCartRepository.save(shoppingCart);
    }

    public Optional<ShoppingCart> getCurrentUserShoppingCart() {

        Long userId = userService.getUser().get().getId();
        return shoppingCartRepository.findByUserId(userId);
    }

}
