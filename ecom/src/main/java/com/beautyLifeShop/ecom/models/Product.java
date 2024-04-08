package com.beautyLifeShop.ecom.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table
@Getter
@Setter
@NoArgsConstructor
public class Product  {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String url;
    private double price;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Rating> ratings;

    private String description;



}
