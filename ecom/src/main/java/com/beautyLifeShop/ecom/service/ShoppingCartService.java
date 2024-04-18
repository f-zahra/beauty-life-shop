package com.beautyLifeShop.ecom.service;

import com.beautyLifeShop.ecom.models.CartItem;
import com.beautyLifeShop.ecom.models.Product;
import com.beautyLifeShop.ecom.models.ShoppingCart;
import com.beautyLifeShop.ecom.repository.ProductRepository;
import jakarta.servlet.http.HttpServletResponse;
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



    private static final String SESSION_ATTRIBUTE_NAME = "shoppingCart";

    public void addItemToCart(ShoppingCart shoppingCart, Long productId, HttpSession session) {

        //verify if the same product exist in shopping cart

        Optional<CartItem> foundProduct = shoppingCart.getCartItems().stream().filter(e -> e.getProduct().getId().equals(productId)).findFirst();

        //get item
         CartItem cartItem;
         if (!foundProduct.isPresent()) {
             //search for product
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));

            // Create a new CartItem and associate it with the shopping cart

            cartItem = new CartItem();
            Random rnd = new Random();
            cartItem.setItemId(String.valueOf(rnd.nextLong()));
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setTotal(product.getPrice());
            cartItem.setShoppingCart(shoppingCart);
             // Add the cart item to the shopping cart
             shoppingCart.getCartItems().add(cartItem);
            shoppingCart.incrementQuantity();

        }
        else {
                foundProduct.get().incrementQuantity();


         }

        shoppingCart.resetTotal();
        session.setAttribute(SESSION_ATTRIBUTE_NAME, shoppingCart);


       //session.setAttribute(SESSION_ATTRIBUTE_NAME, shoppingCart);

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




    public void removeItemFromCart(ShoppingCart cart, String cartId, HttpSession session) {
        //verify if the same product exist in shopping cart
        Optional<CartItem> foundItem = cart.getCartItems().stream()
                .filter(item -> item.getItemId().equals(cartId))
                .findFirst();

         if(!foundItem.isEmpty()){

             cart.getCartItems().remove(foundItem.get());
            // session.setAttribute(SESSION_ATTRIBUTE_NAME, cart);
             //update cart

             cart.decrementQuantity();


             cart.resetTotal();
         }else {

         }

       session.setAttribute(SESSION_ATTRIBUTE_NAME, cart);

    }

    public void removeItem(ShoppingCart cart, Long productId, HttpSession session) {

        Optional<CartItem> foundProduct = cart.getCartItems().stream().filter(e -> e.getProduct().getId().equals(productId)).findFirst();

        if(!foundProduct.isEmpty()) {
            if (foundProduct.get().getQuantity() > 1) {
                foundProduct.get().decrementQuantity();
            } else {
                cart.getCartItems().remove(foundProduct.get());
            }
        }

        cart.resetTotal();
        session.setAttribute(SESSION_ATTRIBUTE_NAME, cart);
    }
}
