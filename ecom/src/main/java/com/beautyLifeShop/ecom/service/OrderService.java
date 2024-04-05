package com.beautyLifeShop.ecom.service;


import com.beautyLifeShop.ecom.models.Address;
import com.beautyLifeShop.ecom.models.Order;
import com.beautyLifeShop.ecom.models.ShoppingCart;
import com.beautyLifeShop.ecom.models.User;
import com.beautyLifeShop.ecom.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private ShoppingCartService shoppingCartService;




    public Order placeOrder(Order order){
        ShoppingCart userCart = shoppingCartService.getCurrentUserShoppingCart().get();
        boolean orderExists = orderRepository.existsByShoppingCart(userCart);
        // Check if the user already has an existing order
        if (orderExists) {
            // Handle case where user already has an existing order
            throw new IllegalStateException("User already has an existing order associated with their shopping cart.");
        }

        order.setShoppingCart(userCart);
        //associate user to address
        order.getShippingAddress().setUser(userCart.getUser());
        //get shopping cart details
      return  orderRepository.save(order);

    }





}
