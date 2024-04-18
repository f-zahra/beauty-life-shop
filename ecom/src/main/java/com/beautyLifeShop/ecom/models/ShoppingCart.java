package com.beautyLifeShop.ecom.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart implements Serializable{



    /* @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Long id;
    private boolean isEmpty = true;
    private int quantity;
    private double Total;
  // @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
   @JsonManagedReference
    private List<CartItem> cartItems = new ArrayList<>();
    private final Double shippingCost = 9.55;

    public ShoppingCart(boolean isEmpty, int quantity, double total, List<CartItem> cartItems) {
        this.isEmpty = isEmpty;
        this.quantity = quantity;
        Total = total;
        this.cartItems = cartItems;
    }



    public void addItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void incrementQuantity(){
        this.quantity++;
    }

    public void decrementQuantity(){

        this.quantity--;

    }





    public void resetTotal(){


        if(cartItems.isEmpty())

        {   this.Total = 0;
            this.quantity=0;
            this.isEmpty =true;}

        else {
            this.isEmpty=false;

           this.Total =  cartItems.stream()
                    .mapToDouble(CartItem::getTotal) // Map each item to its quantity
                    .sum();
        }




    }


    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // Additional logic if needed
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Additional logic if needed
    }


}
