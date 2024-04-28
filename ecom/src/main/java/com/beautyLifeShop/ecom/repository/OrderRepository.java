package com.beautyLifeShop.ecom.repository;

import com.beautyLifeShop.ecom.models.Address;
import com.beautyLifeShop.ecom.models.Order;
import com.beautyLifeShop.ecom.models.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    @Override
    @Query("SELECT o FROM Order o WHERE o.orderId = :orderId")
    Optional<Order> findById(Long orderId);

    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findByUserId(@Param("userId") Long userId);

    @Query("SELECT o FROM Order o")
    List<Order> findAllOrders();

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.orderStatus = :orderStatus, o.shippingAddress.id = :shippingAddressId WHERE o.orderId = :orderId")
    int updateOrder(
            @Param("orderId") Long orderId,
            @Param("orderStatus") OrderStatus orderStatus,
            @Param("shippingAddressId") Long shippingAddressId
    );


}
