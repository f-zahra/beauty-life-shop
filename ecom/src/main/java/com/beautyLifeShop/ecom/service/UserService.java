package com.beautyLifeShop.ecom.service;

import com.beautyLifeShop.ecom.models.User;
import com.beautyLifeShop.ecom.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements  UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    public User registerNewUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            return Optional.of(userOptional.get());

        }else  return Optional.ofNullable(userOptional.get());

    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            var userObj = user.get();
            System.out.println(userObj.getPassword());
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(userObj.getEmail())
                    .password(userObj.getPassword())
                    .roles(String.valueOf(userObj.getRole())).build();

            //build user auth details

            return userDetails;
        }
        return null;
    }
}