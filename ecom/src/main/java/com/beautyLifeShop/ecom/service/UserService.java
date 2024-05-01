package com.beautyLifeShop.ecom.service;


import com.beautyLifeShop.ecom.config.Encoder;
import com.beautyLifeShop.ecom.config.ExceptionHandler;
import com.beautyLifeShop.ecom.models.*;
import com.beautyLifeShop.ecom.repository.AddressRepository;
import com.beautyLifeShop.ecom.repository.OrderRepository;
import com.beautyLifeShop.ecom.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
    /***********CRUD user*************/
    public User registerNewUser(UserRequest user) {

           User newUser = new User();
           newUser.setFirstname(user.getFirstName());
           newUser.setLastname(user.getLastName());
           newUser.setEmail(user.getEmail());
           newUser.setPhoneNumber(user.getPhoneNumber());
           //encode the password before save
           String encoded_password = passwordEncoder.passwordEncoder().encode(user.getPassword());
           newUser.setPassword(encoded_password);
           newUser.getAddresses().add(user.getAddress());

           Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
            if(userOptional.isPresent()){
                throw new RuntimeException("ACCOUNT ALREADY EXIST");
            }else {
               return userRepository.save(newUser);
            }


    }



    //get connected user
    public User getConnectedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for this email: " + email));




    }

    public List<User> getAllUser(){


        return userRepository.findAllUsers();
    }

    //update connected user
    public User updateUser(Long userid,UserRequest userRequest) {
        User user = this.getConnectedUser();
        user.setFirstname(userRequest.getFirstName());
        user.setLastname(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setRole(UserRole.valueOf(userRequest.getUserRole()));

        return  userRepository.save(user);
    }


    public User updateClient(Long userid,UserRequest userRequest) {
        User user = userRepository.findById(userid).get();
        user.setFirstname(userRequest.getFirstName());
        user.setLastname(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setRole(UserRole.valueOf(userRequest.getUserRole()));

        return  userRepository.save(user);
    }

    //update user

    //get connected user

    public void  deleteUser(User user){

        userRepository.delete(user);
    }

    /***********CRUD address*************/
    public List<Address> getAddress() {
        //get user
        User user = this.getConnectedUser();
        return user.getAddresses();


    }
    public Address addAddress(Address address) throws ExceptionHandler {

        //verify if user has already an address
        User user = this.getConnectedUser();
        boolean userAlreadyHasAnAddress = !user.getAddresses().isEmpty();
        if(userAlreadyHasAnAddress){
            address.setDefault(false);
        }

        address.setUser(this.getConnectedUser());
        return addressRepository.save(address);

    }


    //update address+set as default
    public Address updateAddress(Address address) throws ExceptionHandler{

            Optional<Address> optionalAddress = addressRepository.findById(address.getId());
            if (optionalAddress.isPresent()) {
              // Save the updated address
                return addressRepository.save(optionalAddress.get());
            } else {

                throw new RuntimeException("Address with ID " + address.getId() + " not found");
            }

    }


    //delete address
    public void deleteAddress(Address address) throws ExceptionHandler{

        address.setUser(null);
            addressRepository.save(address);
    }





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            return null;
        }else{
            //return userdetails
            var userObj = user.get();
            System.out.println(userObj.getPassword());
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(userObj.getEmail())
                    .password(userObj.getPassword())
                    .roles(String.valueOf(userObj.getRole())).build();

            //build user auth details

            return userDetails;
        }

    }



}