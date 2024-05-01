package com.beautyLifeShop.ecom.config;
import com.beautyLifeShop.ecom.models.Token;
import com.beautyLifeShop.ecom.repository.TokenRepository;
import com.beautyLifeShop.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserService userService;
    public boolean isValidToken(String token) {
        Token tokenRecord = tokenRepository.findByToken(token);
        return tokenRecord != null && !isTokenExpired(tokenRecord);
    }

    public UserDetails getUserByToken(String token) {
        Token tokenRecord = tokenRepository.findByToken(token);
        if (tokenRecord != null) {
            return userService.loadUserByUsername(tokenRecord.getUsername());
        }
        return null;
    }

    public void storeToken(String token, UserDetails userDetails) {
        Token newToken = new Token();
        newToken.setToken(token);
        newToken.setUsername(userDetails.getUsername());
       // newToken.setExpiresAt(expiresAt);

        tokenRepository.save(newToken); // Store the token in the database
    }

    private boolean isTokenExpired(Token tokenRecord) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return tokenRecord.getExpiresAt() != null && tokenRecord.getExpiresAt().before(now);
    }
    public void removeToken(String token) {
        Token foundToken = tokenRepository.findByToken(token);
        tokenRepository.delete(foundToken); // Remove the token from the store
    }
}
