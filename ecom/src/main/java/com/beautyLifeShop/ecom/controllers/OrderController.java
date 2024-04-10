package com.beautyLifeShop.ecom.controllers;


import com.beautyLifeShop.ecom.models.Order;
import com.beautyLifeShop.ecom.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping(path="api/place-order")
    public Order placeOrder(@RequestBody Order order, HttpSession session)
    {
          return orderService.placeOrder(order, session);

    }

    @GetMapping(path="api/order")
    public String placeOrder(HttpSession session)
    {
        return "order page";

    }


}
