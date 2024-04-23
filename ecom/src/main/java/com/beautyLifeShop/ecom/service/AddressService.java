package com.beautyLifeShop.ecom.service;


import com.beautyLifeShop.ecom.config.ExceptionHandler;
import com.beautyLifeShop.ecom.models.Address;
import com.beautyLifeShop.ecom.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;



    //read
    public Address getAddress(Long id){
        return addressRepository.findById(id).orElseThrow(()-> new RuntimeException("address not found"));
    }

    public List<Address> getAllAddresses()throws ExceptionHandler {
        return addressRepository.findAll();
    }


    //update
    public Address updateAddress(Long id) throws  ExceptionHandler{
        //find address
        Address addressFound = this.getAddress(id);
        //update fields
       return addressRepository.save(addressFound);
    }

    //delete
    //add
}
