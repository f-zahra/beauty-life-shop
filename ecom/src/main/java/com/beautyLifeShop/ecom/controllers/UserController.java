package com.beautyLifeShop.ecom.controllers;

import com.beautyLifeShop.ecom.models.Address;
import com.beautyLifeShop.ecom.models.User;
import com.beautyLifeShop.ecom.models.Order;
import com.beautyLifeShop.ecom.models.UserRequest;
import com.beautyLifeShop.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    @Autowired
    private UserService userService;



    /******crud user*******/
    @PostMapping("api/user/register")
    public User createUser(@RequestBody UserRequest user) {


        return userService.registerNewUser(user);
    }

    @GetMapping("api/user/user-dashboard")
    public User getUser() {



        return userService.getConnectedUser();
    }

    @GetMapping({"api/sales-person/users","api/admin/users"})
    public List<User> getUsers(){
        return userService.getAllUser();
    }
    //update user
    @PutMapping("api/sales-person/user-dashboard")
    public User updateUser(@RequestParam Long userId, @RequestBody UserRequest userRequest){
        return userService.updateUser(userId, userRequest);
    }
    @PutMapping({"api/user/update-user"})
    public User updateAuthenticatedUser(@RequestBody UserRequest userRequest){
        //get authenticated user
        User user = userService.getConnectedUser();
        return userService.updateUser(user.getId(), userRequest);
    }
    @DeleteMapping("api/admin/delete-user")
    public void deleteUser(@RequestBody User user){
        userService.deleteUser(user);
    }
    //delete user
    /******crud address*******/
    @GetMapping({"api/user/address-list","api/sales-person/address-list"})
    public List<Address> getAddresses(){
        return userService.getAddress();
    }


    @PostMapping({"api/user/add-address","api/sales-person/add-address"})
    public Address addAddress(@RequestBody Address address){
        return userService.addAddress(address);

    }

    //update address
    @PutMapping({"api/user/update-address","api/sales-person/update-address"})
    public Address updateAddress(@RequestBody Address address){
        return  userService.updateAddress(address);
    }

    //delete address
    @DeleteMapping({"api/user/delete-address","api/sales-person/delete-address"})
    public void deleteAddress(@RequestBody Address address){
          userService.deleteAddress(address);
    }







}
