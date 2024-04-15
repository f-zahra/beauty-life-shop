package com.beautyLifeShop.ecom.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem  implements  Serializable{

         //@Id

        private String itemId;
        private int quantity;

        @JsonBackReference
        private ShoppingCart shoppingCart;


        private Product product;
            private Double Total;


        public void incrementQuantity(){
            if(this.quantity<5) { this.quantity++; updateTotal();}

        }

        public void decrementQuantity(){
        if(this.quantity>0) { this.quantity--; updateTotal();}

             }

    public void updateTotal(){
         this.Total =  this.quantity*this.product.getPrice();
    }

}
