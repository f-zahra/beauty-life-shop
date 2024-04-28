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
    public ResponseEntity<String> createUser(@RequestBody UserRequest user) {


        userService.registerNewUser(user);
        return ResponseEntity.ok("user registered");
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
    @PutMapping({"api/sales-person/update-user"})
    public ResponseEntity<String> updateUser(@RequestParam Long userId, @RequestBody UserRequest user){
        userService.updateUser(userId, user);
        return ResponseEntity.ok("user updated");
    }

    @PutMapping({"api/admin/update-user"})
    public ResponseEntity<String> updateClient(@RequestParam Long userId, @RequestBody UserRequest user){
        userService.updateClient(userId, user);
        return ResponseEntity.ok("user updated");
    }
    @PutMapping({"api/user/update-user"})
    public User updateAuthenticatedUser(@RequestBody UserRequest userRequest){
        //get authenticated user
        User user = userService.getConnectedUser();
        return userService.updateUser(user.getId(), userRequest);
    }
    @DeleteMapping("api/admin/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody User user){

        userService.deleteUser(user);
        return ResponseEntity.ok("userdeleted");
    }
    //delete user
    /******crud address*******/
    @GetMapping({"api/user/address-list","api/sales-person/address-list"})
    public List<Address> getAddresses(){
        return userService.getAddress();
    }


    @PostMapping({"api/user/add-address","api/sales-person/add-address"})
    public ResponseEntity<String> addAddress(@RequestBody Address address){
       userService.addAddress(address);
       return ResponseEntity.ok("address was added");

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
