package com.beautyLifeShop.ecom.models;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Table(name = "user_order")
@Getter
@Setter
@NoArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_order", sequenceName = "user_order_seq", allocationSize = 1)
    private Long orderId;
    private Date orderDate;
    @OneToOne(cascade = CascadeType.ALL)
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PENDING;




}
