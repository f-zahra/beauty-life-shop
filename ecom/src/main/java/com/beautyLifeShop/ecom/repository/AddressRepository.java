package com.beautyLifeShop.ecom.repository;


import com.beautyLifeShop.ecom.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Long> {
}
