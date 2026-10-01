package com.store.inventory.repository;

import com.store.inventory.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAll();
    Optional<Order> findById(Integer Id);
    List<Order> findByOrderStatus(Integer orderStatus);
    List<Order> findByPurchaseDate(LocalDate purchaseDate);
}
