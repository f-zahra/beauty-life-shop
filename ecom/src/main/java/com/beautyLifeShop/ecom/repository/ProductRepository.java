package com.beautyLifeShop.ecom.repository;

import com.beautyLifeShop.ecom.models.Product;
import com.beautyLifeShop.ecom.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findById(Long id);
    List<Product> findAll();
}
