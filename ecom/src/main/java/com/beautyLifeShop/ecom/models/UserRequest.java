package com.beautyLifeShop.ecom.models;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private String phoneNumber;
    private String userRole;
}
