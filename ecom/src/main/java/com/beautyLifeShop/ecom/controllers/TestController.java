package com.beautyLifeShop.ecom.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {



    @GetMapping("/api/test")
    public String getTest(){

        return " test page ";
    }
}
