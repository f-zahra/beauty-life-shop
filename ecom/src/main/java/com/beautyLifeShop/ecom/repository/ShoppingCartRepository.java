package com.beautyLifeShop.ecom.repository;

import com.beautyLifeShop.ecom.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ShoppingCartRepository  extends JpaRepository<ShoppingCart, Long> {

    //get shopping cart by userid
    @Query("SELECT sc FROM ShoppingCart sc WHERE sc.user.id = :userId")
   Optional<ShoppingCart> findByUserId(@Param("userId") Long id);





}
