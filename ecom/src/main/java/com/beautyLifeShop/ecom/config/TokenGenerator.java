package com.beautyLifeShop.ecom.config;

import java.util.UUID;

public  class TokenGenerator {

    public static String generateToken() {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();
        // Convert UUID to String and return
        return uuid.toString();
    }

    public static void main(String[] args) {
        // Generate a token
        String token = generateToken();
        System.out.println("Generated Token: " + token);
    }
}
