package com.beautyLifeShop.ecom.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserRequest {


    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Address address;
    private int phoneNumber;
}
