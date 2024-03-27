package com.beautyLifeShop.ecom.controllers;

import com.beautyLifeShop.ecom.models.User;
import com.beautyLifeShop.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("api/user-registration")
    public User registerNewUser(@RequestBody User user){
     return userService.registerNewUser(user);
    }


    @GetMapping("/api/admin")
    public String admin(){
        return "admin page";
    }
    @GetMapping("/api/user")
    public String user(){
        return "user page";
    }



}
