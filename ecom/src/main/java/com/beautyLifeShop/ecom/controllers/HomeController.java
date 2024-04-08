package com.beautyLifeShop.ecom.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
public class HomeController {

    @GetMapping("api/home")
    public String sayWelcome(){

        return RequestContextHolder.currentRequestAttributes().getSessionId();

    }
}
