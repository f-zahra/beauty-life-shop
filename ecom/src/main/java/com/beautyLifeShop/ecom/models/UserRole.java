package com.beautyLifeShop.ecom.models;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum UserRole {

    USER,
    ADMIN,
    VISITOR,
    SALESPERSON



}
