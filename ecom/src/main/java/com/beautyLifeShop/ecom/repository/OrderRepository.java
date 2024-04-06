package com.beautyLifeShop.ecom.repository;

import com.beautyLifeShop.ecom.models.Order;
import com.beautyLifeShop.ecom.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    @Override
    Optional<Order> findById(Long Long);

    //boolean existsByShoppingCart(ShoppingCart shoppingCart);
}