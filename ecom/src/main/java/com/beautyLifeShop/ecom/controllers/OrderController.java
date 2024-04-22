package com.beautyLifeShop.ecom.controllers;


import com.beautyLifeShop.ecom.models.Order;
import com.beautyLifeShop.ecom.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    //add
    @PostMapping(path="api/user/place-order")
    public Order placeOrder(@RequestBody Order order, HttpSession session)
    {
          return orderService.placeOrder(order, session);

    }

    //view
    @GetMapping("api/sales-person/orders")
    public List<Order> getAllOrders(){
        return  orderService.getOrders();
    }
    //update



    //delete







}
