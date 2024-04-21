package com.beautyLifeShop.ecom.models;


import lombok.Data;
import lombok.Getter;

@Getter
public enum OrderStatus {

    PENDING,
    SHIPPED,
    DELIVERED,
    CANCELED

}
