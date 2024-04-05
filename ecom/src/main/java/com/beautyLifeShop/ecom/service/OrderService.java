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


       // order.setShoppingCart(userCart);
        //associate user to address
        order.getShippingAddress().setUser(userCart.getUser());
        order = orderRepository.save(order);

        shoppingCartService.removeAllItems(userCart);

        return  order;

    }





}
