package com.store.inventory.controller;

import com.store.inventory.dto.OrderDTO;
import com.store.inventory.dto.ProductDTO;
import com.store.inventory.entity.Order;
import com.store.inventory.entity.Product;
import com.store.inventory.service.ProductService;
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
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar nuevo producto", description = "Registra un nuevo producto.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Producto registrado correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al registrar el producto", content = @Content)
            })
    public ResponseEntity<Product> register(@Valid @RequestBody ProductDTO request) {
        return ResponseEntity.ok(productService.saveProduct(request));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un producto", description = "Actualiza un producto.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Producto acutalizada correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al actualizar el producto", content = @Content)
            })
    public ResponseEntity<Product> update(@Valid @RequestBody ProductDTO request) {
        return ResponseEntity.ok(productService.updateProduct(request));
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos losproductos", description = "Obtiene todos los productos.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al consultar los productos", content = @Content)
            })
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping(value = "/{idProduct}",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene un producto", description = "Obtiene un producto.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Producto obtenid correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al obtener el producto", content = @Content)
            })
    public ResponseEntity<Product> getProducto(@Parameter(description = "Identificador del registro")
                                          @Positive
                                          @PathVariable
                                          Integer productId) {
        return ResponseEntity.ok(productService.findByProductId(productId));
    }

    @DeleteMapping(value = "/{idProduct}",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un producto", description = "Elimina unv producto.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno al eliminar el producto", content = @Content)
            })
    public ResponseEntity<Void> deleteProduct(@Parameter(description = "Identificador del registro")
                                            @Positive
                                            @PathVariable
                                            Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
