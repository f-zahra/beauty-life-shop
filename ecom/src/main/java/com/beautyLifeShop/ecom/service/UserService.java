package com.beautyLifeShop.ecom.service;


import com.beautyLifeShop.ecom.config.Encoder;
import com.beautyLifeShop.ecom.config.ExceptionHandler;
import com.beautyLifeShop.ecom.models.Address;
import com.beautyLifeShop.ecom.models.User;
import com.beautyLifeShop.ecom.models.UserRequest;
import com.beautyLifeShop.ecom.repository.AddressRepository;
import com.beautyLifeShop.ecom.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements  UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private Encoder passwordEncoder;
    public User registerNewUser(UserRequest user) throws RuntimeException {

           User newUser = new User();
           newUser.setFirstname(user.getFirstName());
           newUser.setLastname(user.getLastName());
           newUser.setEmail(user.getEmail());

           //encode the password before save
           String encoded_password = passwordEncoder.passwordEncoder().encode(user.getPassword());
           newUser.setPassword(encoded_password);
           newUser.getAddresses().add(user.getAddress());
           return userRepository.save(newUser);


    }


    public List<Address> getAddress() {
        //get user
        User user = this.getUser();
         return user.getAddresses();


    }
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()) {

            throw new RuntimeException();
        }
        return userOptional.get();
    }



    public Address addAddress(Address address) throws ExceptionHandler {

        //verify if user has already an address
        User user = this.getUser();
        boolean userAlreadyHasAnAddress = !user.getAddresses().isEmpty();
        if(userAlreadyHasAnAddress){
            address.setDefault(false);
        }

        address.setUser(this.getUser());
        return addressRepository.save(address);

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