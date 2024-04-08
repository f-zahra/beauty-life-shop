package com.beautyLifeShop.ecom.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity

@Getter
@Setter
@NoArgsConstructor
public class Rating  {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String details;
    private int note=0;
    @ManyToOne()
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;
}
