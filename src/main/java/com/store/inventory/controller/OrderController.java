package com.store.inventory.controller;

import com.store.inventory.dto.OrderDTO;
import com.store.inventory.entity.Order;
import com.store.inventory.service.OrderService;
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
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar nueva orden de compra", description = "Registra una nueva orden de compra.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Orden registrada correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al registrar la orden", content = @Content)
            })
    public ResponseEntity<Order> register(@Valid @RequestBody OrderDTO request) {
        return ResponseEntity.ok(orderService.registerOrder(request));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza una orden de compra", description = "Actualiza una orden de compra.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Orden acutalizada correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al actualizar la orden", content = @Content)
            })
    public ResponseEntity<Order> update(@Valid @RequestBody OrderDTO request) {
        return ResponseEntity.ok(orderService.updateOrder(request));
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todas las órdenes de compra", description = "Obtiene todas la órdenes de compra.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista de órdenes obtenida correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al consultar las órdenes", content = @Content)
            })
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping(value = "/{idOrder}",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene un orden de compra", description = "Obtiene una orden de compra.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Orden obtenida correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al obtener la orden", content = @Content)
            })
    public ResponseEntity<Order> getOrden(@Parameter(description = "Identificador del registro")
                                              @Positive
                                              @PathVariable
                                              Integer idOrder) {
        return ResponseEntity.ok(orderService.findById(idOrder));
    }

    @DeleteMapping(value = "/{idOrder}",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un orden de compra", description = "Elimina una orden de compra.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Orden eliminada correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al eliminar la orden", content = @Content)
            })
    public ResponseEntity<Void> deleteOrden(@Parameter(description = "Identificador del registro")
                                          @Positive
                                          @PathVariable
                                          Integer idOrder) {
        orderService.deleteOrder(idOrder);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
