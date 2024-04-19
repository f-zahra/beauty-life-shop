package com.beautyLifeShop.ecom.models;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItem> items = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PENDING;
    @ManyToOne
    @JsonBackReference
    private User user;






}
