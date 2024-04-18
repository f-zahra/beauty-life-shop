package com.beautyLifeShop.ecom.controllers;


import com.beautyLifeShop.ecom.config.TokenGenerator;
import com.beautyLifeShop.ecom.models.LoginRequest;
import com.beautyLifeShop.ecom.models.ResponseRequest;
import com.beautyLifeShop.ecom.models.User;
import com.beautyLifeShop.ecom.repository.UserRepository;
import com.beautyLifeShop.ecom.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession httpSession;


    @PostMapping("api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {


        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
            httpSession.setAttribute("user", userDetails); // Store user in session


            // Generate token (You can use any token generation mechanism here)
            String token = TokenGenerator.generateToken();


            // Return token in response
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

    }

}
