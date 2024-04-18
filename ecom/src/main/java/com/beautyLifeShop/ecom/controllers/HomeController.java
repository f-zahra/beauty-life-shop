package com.beautyLifeShop.ecom.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("api/home")
    public ResponseEntity<String> sayWelcome(){
        return ResponseEntity.ok("Welcome to the online shop");

    }
}
