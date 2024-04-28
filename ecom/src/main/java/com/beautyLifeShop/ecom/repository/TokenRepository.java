package com.beautyLifeShop.ecom.repository;


import com.beautyLifeShop.ecom.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {


    @Query("SELECT t FROM Token t WHERE t.token=:token")
    Token findByToken(@Param("token")String token);
}
