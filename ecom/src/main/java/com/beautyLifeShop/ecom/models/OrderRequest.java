package com.beautyLifeShop.ecom.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class OrderRequest {

    private  Address shippingAddress;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
