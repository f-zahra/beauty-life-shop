package com.beautyLifeShop.ecom.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private int quantity;
        @ManyToOne
        @JoinColumn(name = "shopping_cart_id")
        @JsonBackReference
        private ShoppingCart shoppingCart;
        @ManyToOne
        @JoinColumn(name = "product_id")

        private Product product;


}
