package com.beautyLifeShop.ecom.service;

import com.beautyLifeShop.ecom.models.CartItem;
import com.beautyLifeShop.ecom.models.Product;
import com.beautyLifeShop.ecom.models.ShoppingCart;
import com.beautyLifeShop.ecom.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;


    @Autowired
    private HttpSession httpSession;

    private static final String SESSION_ATTRIBUTE_NAME = "shoppingCart";

    public void addItemToCart(ShoppingCart shoppingCart, Long productId, HttpSession session) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));

        // Create a new CartItem and associate it with the shopping cart
        CartItem cartItem = new CartItem();
        Random rnd = new Random(50);
        cartItem.setId(rnd.nextLong());
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setShoppingCart(shoppingCart);


        //itemRepository.save(cartItem);
        // Add the cart item to the shopping cart
         shoppingCart.getCartItems().add(cartItem);


        //get number of items in the shopping cart
        int cart_item_qt =  shoppingCart.getCartItems().size();
        //get qt of products per item
        int quantity_per_item = cartItem.getQuantity();
        //get bill total per item
        double totalPrice_per_item = cartItem.getProduct().getPrice();

        shoppingCart.setQuantity(cart_item_qt);


        //set total

        shoppingCart.setTotal(shoppingCart.getQuantity()*quantity_per_item*totalPrice_per_item);
        shoppingCart.setEmpty(false);

       // shoppingCartRepository.save(shoppingCart);
        session.setAttribute(SESSION_ATTRIBUTE_NAME, shoppingCart);

    }



    public ShoppingCart getCart(HttpSession session) {

        ShoppingCart cart = (ShoppingCart) session.getAttribute(SESSION_ATTRIBUTE_NAME);

        if (cart == null) {
            Random rnd = new Random(50);
            cart = new ShoppingCart();
            cart.setId(rnd.nextLong());
            cart.setCartItems(new ArrayList<>());

            session.setAttribute(SESSION_ATTRIBUTE_NAME, cart);


        }


        return cart;
    }

    public void updateShoppingCart(HttpSession session, ShoppingCart shoppingCart) {
        // Update the shopping cart in the session
        session.setAttribute(SESSION_ATTRIBUTE_NAME, shoppingCart);
    }


   /* @Transactional
    public void removeAllItems(Optional<ShoppingCart> userCart) {

        userCart.getCartItems().stream().forEach
                (e -> itemRepository.delete(e));
        userCart.getCartItems().clear();
        userCart.setTotal(0);
        userCart.setQuantity(0);
        userCart.setEmpty(true);
    }*/




   
}
