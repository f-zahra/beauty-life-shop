package com.beautyLifeShop.ecom.service;


import com.beautyLifeShop.ecom.config.ExceptionHandler;
import com.beautyLifeShop.ecom.models.*;
import com.beautyLifeShop.ecom.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;




    public Order placeOrder(Order order, HttpSession session) throws ExceptionHandler{

        //get shopping cart

        ShoppingCart userCart = shoppingCartService.getCart(session);
       //get user
        User user = userService.getUser();

       //creating new order items and adding them to order
        if(!userCart.isEmpty()){
       userCart.getCartItems().stream().forEach(i -> {
           OrderItem orderItem = new OrderItem();
           orderItem.setQuantity(i.getQuantity());
            orderItem.setOrder(order);
             orderItem.setProduct(i.getProduct());
             //add item to order
                order.getItems().add(orderItem);
       });}else {
            throw  new RuntimeException("cart is empty");
        }
       //set user

        order.setUser(user);
        //when a user submit new shipping address form it uses addAddress func
        orderRepository.save(order);
       session.setAttribute("shoppingCart", null);

        return  order;

    }

    //fetch all users orders
    public List<Order> getOrders() throws ExceptionHandler {

      return orderRepository.findAll();
    }




}
