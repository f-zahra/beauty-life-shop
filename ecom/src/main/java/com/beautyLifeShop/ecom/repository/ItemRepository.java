package com.beautyLifeShop.ecom.repository;

import com.beautyLifeShop.ecom.models.CartItem;
import com.sun.mail.imap.protocol.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<CartItem, Long> {


}
