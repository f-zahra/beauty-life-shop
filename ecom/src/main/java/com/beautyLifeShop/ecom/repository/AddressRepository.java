package com.beautyLifeShop.ecom.repository;


import com.beautyLifeShop.ecom.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Long> {

    @Override
    Optional<Address> findById(Long aLong);

    @Modifying
    @Query("DELETE FROM Address a WHERE a.id = :id")
    void deleteByAddressId(@Param("id")Long id);


}
