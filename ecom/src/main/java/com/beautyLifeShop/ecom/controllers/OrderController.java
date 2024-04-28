package com.beautyLifeShop.ecom.controllers;


import com.beautyLifeShop.ecom.models.Order;
import com.beautyLifeShop.ecom.models.OrderRequest;
import com.beautyLifeShop.ecom.models.User;
import com.beautyLifeShop.ecom.service.OrderService;
import com.beautyLifeShop.ecom.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


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
    //view user order history

    @GetMapping("api/user/order-history")
    public List<Order> getUserOrder(){
        User user = userService.getConnectedUser();
        return  orderService.getUserOrders(user.getId());
    }


    //update  order
    @PutMapping({"/api/user/update-order", "/api/sales-person/update-order"})
    public ResponseEntity<String> updateOrder(@RequestParam Long orderId, @RequestBody OrderRequest orderRequest){

        orderService.updateOrder(orderId, orderRequest);
        return ResponseEntity.ok("order updated");
    }


    //delete order
    @DeleteMapping("/api/admin/delete-order")
    public void deleteOrder(@RequestParam Long orderId){
        orderService.deleteOrder(orderId);
    }






    //







}
