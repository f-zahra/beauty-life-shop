package com.beautyLifeShop.ecom.service;

import com.beautyLifeShop.ecom.models.CartItem;
import com.beautyLifeShop.ecom.models.Product;
import com.beautyLifeShop.ecom.models.ShoppingCart;
import com.beautyLifeShop.ecom.models.User;
import com.beautyLifeShop.ecom.repository.ProductRepository;
import com.beautyLifeShop.ecom.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;


   /* public Optional<ShoppingCart> getShoppingCart() throws CartNotFound {


            Long userId = userService.getUser().get().getId();
        Optional<ShoppingCart> cart =  shoppingCartRepository.findByUserId(userId);
        if(!cart.isPresent()) {
            throw new CartNotFound("cart not found");
        } else return cart;




    }
*/




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

        //get user id
        User user = userService.getUser().get();
        //find shopping cart
        Optional<ShoppingCart> cart = shoppingCartRepository.findByUserId(user.getId());
        if(!cart.isPresent()) {
            //instan cart
            ShoppingCart newCart = new ShoppingCart();
            newCart.setUser(user);
            shoppingCartRepository.save(newCart);

            return Optional.of(newCart);

        }else return cart;

    }

}
