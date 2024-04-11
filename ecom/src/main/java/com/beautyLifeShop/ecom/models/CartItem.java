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

        private Long id;
        private int quantity;
 /*   @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    @JsonIgnore*/
        @JsonBackReference
        private ShoppingCart shoppingCart;
          /*      @ManyToOne
        @JoinColumn(name = "product_id")*/

        private Product product;


}
