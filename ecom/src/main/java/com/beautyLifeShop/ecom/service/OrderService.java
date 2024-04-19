package com.beautyLifeShop.ecom.service;


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





    public Order placeOrder(Order order, HttpSession session){


       ShoppingCart userCart = shoppingCartService.getCart(session);
       userCart.getCartItems().stream().forEach(i -> {
           OrderItem orderItem = new OrderItem();
           orderItem.setQuantity(i.getQuantity());
  orderItem.setOrder(order);
orderItem.setProduct(i.getProduct());
       order.getItems().add(orderItem);
       });

       orderRepository.save(order);
       session.setAttribute("shoppingCart", null);

        return  order;

    }


    //fetch all users orders

}
