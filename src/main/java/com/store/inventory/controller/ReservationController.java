package com.store.inventory.controller;

import com.store.inventory.dto.OrderDTO;
import com.store.inventory.dto.ReservationDTO;
import com.store.inventory.entity.Order;
import com.store.inventory.entity.Reservation;
import com.store.inventory.service.OrderService;
import com.store.inventory.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar nueva reserva", description = "Registra una nueva reserva.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Resrva registrada correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al registrar la reserva", content = @Content)
            })
    public ResponseEntity<Reservation> register(@Valid @RequestBody ReservationDTO request) {
        return ResponseEntity.ok(reservationService.saveReservation(request));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza una reserva", description = "Actualiza una reserva.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Reserva acutalizada correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al actualizar la reserva", content = @Content)
            })
    public ResponseEntity<Reservation> update(@Valid @RequestBody ReservationDTO request) {
        return ResponseEntity.ok(reservationService.updateReservation(request));
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todas las reservas", description = "Obtiene todas las reservas.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista de reservas obtenida correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al consultar las reservas", content = @Content)
            })
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping(value = "/{reservationId}",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene un reserva", description = "Obtiene una reserva.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Reserva obtenida correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al obtener la reserva", content = @Content)
            })
    public ResponseEntity<Reservation> getRservation(@Parameter(description = "Identificador del registro")
                                          @Positive
                                          @PathVariable
                                          Integer reservationId) {
        return ResponseEntity.ok(reservationService.findBiId(reservationId));
    }

    @DeleteMapping(value = "/{reservationId}",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina una reserva", description = "Elimina una reserva.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Reserva eliminada correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al eliminar la reserva", content = @Content)
            })
    public ResponseEntity<Void> deleteReservation(@Parameter(description = "Identificador del registro")
                                            @Positive
                                            @PathVariable
                                            Integer reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
