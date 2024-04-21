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
    private OrderRepository orderRepository;

    @Autowired
    private Encoder passwordEncoder;
    public User registerNewUser(UserRequest user) {

           User newUser = new User();
           newUser.setFirstname(user.getFirstName());
           newUser.setLastname(user.getLastName());
           newUser.setEmail(user.getEmail());

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


    public List<Address> getAddress() {
        //get user
        User user = this.getUser();
         return user.getAddresses();


    }
    public User getUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for email: " + email));




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

        Optional<Address> optionalAddress = addressRepository.findById(address.getId());
        if (optionalAddress.isPresent()) {
            // Save the updated address
            addressRepository.deleteById(address.getId());
        } else {

            throw new RuntimeException("Address with ID " + address.getId() + " not found");
        }

    }


    public List<Order> getUserOrders(Long userId) throws ExceptionHandler{

        List<Order> order =  orderRepository.findByUserId(userId);
        if(!order.isEmpty())
            return order;
           else
        { throw new RuntimeException("no order found");}


    }

    //update user Order ()
    public Order updateOrder(Order order)throws ExceptionHandler{
        //find order
        Optional<Order> orderOptional = orderRepository.findById(order.getOrderId());
        if(orderOptional.isEmpty()){
            throw new RuntimeException("order not found");

        }
        if(!orderOptional.get().getOrderStatus().equals(OrderStatus.PENDING)){
            throw new RuntimeException("cannot cancel order");
        }

            else {
            orderOptional.get().setShippingAddress(order.getShippingAddress());
            orderOptional.get().setOrderStatus(order.getOrderStatus());
            return orderRepository.save(orderOptional.get());
        }


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