package com.beautyLifeShop.ecom.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ShoppingCart {


  /* @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Long id;
    private boolean isEmpty = true;
    private int quantity;
    private double Total;
  // @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
   @JsonManagedReference
    private List<CartItem> cartItems;


    public ShoppingCart(boolean isEmpty, int quantity, double total, List<CartItem> cartItems) {
        this.isEmpty = isEmpty;
        this.quantity = quantity;
        Total = total;
        this.cartItems = cartItems;
    }

}
