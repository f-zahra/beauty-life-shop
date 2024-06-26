package com.beautyLifeShop.ecom.controllers;


import com.beautyLifeShop.ecom.config.TokenGenerator;
import com.beautyLifeShop.ecom.config.TokenService;
import com.beautyLifeShop.ecom.models.LoginRequest;
import com.beautyLifeShop.ecom.models.ResponseRequest;
import com.beautyLifeShop.ecom.models.Token;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {


        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
            httpSession.setAttribute("user", userDetails); // Store user in session


            // Generate token only if not already generated or if a new session is needed
            String token = (String) httpSession.getAttribute("token");
            if (token == null) {
                token = TokenGenerator.generateToken();
                // Store the token in the TokenService
                tokenService.storeToken(token, userDetails);
                httpSession.setAttribute("token", token);
            }

            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/api/logout")
    public ResponseEntity<Map<String, String>>logout(){
        String token = (String) httpSession.getAttribute("token");

        if (token != null) {
            // Remove the token from the TokenService
            tokenService.removeToken(token); // Implement removeToken in TokenService

            // Clear the session attributes
            httpSession.invalidate(); // Invalidate the entire session
        }

        // Clear the security context
        SecurityContextHolder.clearContext(); // Clear the Spring Security context
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");

        return ResponseEntity.ok(response);

    }
}
