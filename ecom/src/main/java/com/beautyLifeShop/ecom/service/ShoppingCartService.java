package com.beautyLifeShop.ecom.service;

import com.beautyLifeShop.ecom.models.CartItem;
import com.beautyLifeShop.ecom.models.Product;
import com.beautyLifeShop.ecom.models.ShoppingCart;
import com.beautyLifeShop.ecom.models.User;
import com.beautyLifeShop.ecom.repository.ItemRepository;
import com.beautyLifeShop.ecom.repository.ProductRepository;
import com.beautyLifeShop.ecom.repository.ShoppingCartRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    private ItemRepository itemRepository;


    public void addItemToCart(ShoppingCart shoppingCart, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));

        // Create a new CartItem and associate it with the shopping cart
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setShoppingCart(shoppingCart);

        // Add the cart item to the shopping cart
        //getItems
        List<CartItem> cartItemList = shoppingCart.getCartItems();
        cartItemList.add(cartItem);


        //get number of items in the shopping cart
        int cart_item_qt = cartItemList.size();
        //get qt of products per item
        int quantity_per_item = cartItem.getQuantity();
        //get bill total per item
        double totalPrice_per_item = cartItem.getProduct().getPrice();

        shoppingCart.setQuantity(cart_item_qt);


        //set total

        shoppingCart.setTotal(shoppingCart.getQuantity()*quantity_per_item*totalPrice_per_item);
        shoppingCart.setEmpty(false);



        shoppingCartRepository.save(shoppingCart);
    }

    public Optional<ShoppingCart> getCurrentUserShoppingCart() throws EntityNotFoundException {

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




    @Transactional
    public void removeAllItems(ShoppingCart userCart) {

        userCart.getCartItems().stream().forEach
                (e -> itemRepository.delete(e));
        userCart.getCartItems().clear();
        userCart.setTotal(0);
        userCart.setQuantity(0);
    }


    @Transactional
    public void delete(Long id) {

        shoppingCartRepository.deleteById(id);
    }

   
}
