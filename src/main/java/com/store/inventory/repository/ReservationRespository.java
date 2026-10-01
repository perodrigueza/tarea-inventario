package com.store.inventory.repository;

import com.store.inventory.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRespository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAll();
    List<Reservation> findByOrderId(Integer orderId);
    Optional<Reservation> findById(Integer id);
}
