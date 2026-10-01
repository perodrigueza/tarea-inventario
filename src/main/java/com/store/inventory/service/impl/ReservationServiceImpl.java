package com.store.inventory.service.impl;

import com.store.inventory.dto.ReservationDTO;
import com.store.inventory.entity.Reservation;
import com.store.inventory.repository.ReservationRespository;
import com.store.inventory.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRespository reservationRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findByOrderId(Integer orderId) {
        return reservationRepository.findByOrderId(orderId);
    }

    @Override
    public Reservation findBiId(Integer id) {
        return reservationRepository.findById(id).orElseThrow(UnsupportedOperationException::new);
    }

    @Override
    public Reservation saveReservation(ReservationDTO reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setOrderId(reservationDto.getOrderId());
        reservation.setSku(reservationDto.getSku());
        reservation.setQuantity(reservationDto.getQuantity());
        reservation.setExpiresAt(reservationDto.getExpiresAt());
        reservation = reservationRepository.save(reservation);
        return reservation;
    }

    @Override
    public Reservation updateReservation(ReservationDTO  reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationDto.getReservationId());
        reservation.setOrderId(reservationDto.getOrderId());
        reservation.setSku(reservationDto.getSku());
        reservation.setQuantity(reservationDto.getQuantity());
        reservation.setExpiresAt(reservationDto.getExpiresAt());
        reservation = reservationRepository.save(reservation);
        return reservation;
    }

    @Override
    public void deleteReservation(Integer reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
