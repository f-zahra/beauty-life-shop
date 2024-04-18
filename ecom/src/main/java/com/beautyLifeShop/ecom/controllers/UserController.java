package com.beautyLifeShop.ecom.controllers;

import com.beautyLifeShop.ecom.models.Address;
import com.beautyLifeShop.ecom.models.User;
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


    @PostMapping("api/register/user")
    public User createUser(@RequestBody UserRequest user) {


        return userService.registerNewUser(user);
    }

    @GetMapping("api/user")
    public ResponseEntity<User> getUser() {

        Optional<User> user = userService.getUser();

        return ResponseEntity.ok(user.get());
    }


    @GetMapping("api/user/address-list")
    public List<Address> getAddresses(){
        return userService.getAddress();
    }



}
