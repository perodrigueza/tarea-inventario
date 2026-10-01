package com.store.inventory.service;

import com.store.inventory.dto.ReservationDTO;
import com.store.inventory.entity.Reservation;

import java.time.Instant;
import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();
    List<Reservation> findByOrderId(Integer orderId);
    Reservation findBiId(Integer id);
    Reservation saveReservation(ReservationDTO  reservationDto);
    Reservation updateReservation(ReservationDTO  reservationDto);
    void deleteReservation(Integer reservationId);
}
