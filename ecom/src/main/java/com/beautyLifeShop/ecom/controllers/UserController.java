package com.beautyLifeShop.ecom.controllers;

import com.beautyLifeShop.ecom.models.Address;
import com.beautyLifeShop.ecom.models.User;
import com.beautyLifeShop.ecom.models.Order;
import com.beautyLifeShop.ecom.models.UserRequest;
import com.beautyLifeShop.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    @Autowired
    private UserService userService;




    @PostMapping("api/user/register")
    public User createUser(@RequestBody UserRequest user) {


        return userService.registerNewUser(user);
    }

    @GetMapping("api/user")
    public ResponseEntity<User> getUser() {



        return ResponseEntity.ok(userService.getUser());
    }


    @GetMapping("api/user/address-list")
    public List<Address> getAddresses(){
        return userService.getAddress();
    }


    @PostMapping("api/user/add-address")
    public Address addAddress(@RequestBody Address address){
        return userService.addAddress(address);

    }

    @PutMapping("api/user/update-address")
    public Address updateAddress(@RequestBody Address address){
        return  userService.updateAddress(address);
    }

    @DeleteMapping("api/user/delete-address")
    public void deleteAddress(@RequestBody Address address){
          userService.deleteAddress(address);
    }


    //get user order
    @GetMapping("/api/user/order")
    public List<Order> getUserOrders(@RequestParam Long userId){
        return userService.getUserOrders(userId);
    }

    //update user order
    @PostMapping("/api/user/update-order")
    public Order updateOrder(@RequestBody Order order){
        return userService.updateOrder(order);
    }





}
