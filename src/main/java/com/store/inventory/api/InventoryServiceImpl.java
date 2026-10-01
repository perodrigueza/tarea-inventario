package com.store.inventory.api;

import com.store.inventory.dto.ProductDTO;
import com.store.inventory.dto.ReservationDTO;
import com.store.inventory.entity.Order;
import com.store.inventory.entity.Product;
import com.store.inventory.service.OrderService;
import com.store.inventory.service.ProductService;
import com.store.inventory.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService{
    private final ProductService  productService;
    private final ReservationService  reservationService;
    private final OrderService orderService;
    private final StockAlertListener stockAlertListener;
    private final Integer FLASH_SALE_LIMIT = 2;

    @Override
    public void registerProduct(String sku, ProductCategory category) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku(sku);
        productDTO.setProductCategory(category);
        productService.saveProduct(productDTO);
    }

    @Override
    public void addStock(String sku, int quantity) {
        Product product = productService.findBySku(sku);
        if(product != null ) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setQuantity(quantity);
            productDTO.setProductCategory(product.getProductCategory());
            productDTO.setSku(product.getSku());
            productService.updateProduct(productDTO);
        }
    }

    @Override
    public Reservation reserve(String orderId, String sku, int quantity) {
        Product product = productService.findBySku(sku);
        Reservation reservation = null;
        if(product != null){
            if(product.getProductCategory().equals(ProductCategory.FLASH_SALE) && quantity > FLASH_SALE_LIMIT){
                throw new OrderLimitExceededException(product.getSku(), quantity, product.getQuantity());
            }
            if(product.getQuantity() < quantity){
                throw new InsufficientStockException(product.getSku(), quantity, product.getQuantity());
            }

            Instant expiresAt = Instant.now();
            switch(product.getProductCategory()) {
                case STANDARD -> expiresAt.plusSeconds(900);
                case PRE_ORDER -> expiresAt.plusSeconds(86400);
                case FLASH_SALE ->expiresAt.plusSeconds(300);
            }
            ReservationDTO res = new ReservationDTO();
            res.setOrderId(Integer.getInteger(orderId));
            res.setSku(sku);
            res.setQuantity(quantity);
            res.setExpiresAt(expiresAt);
            com.store.inventory.entity.Reservation reser = reservationService.saveReservation(res);
            reservation  = new Reservation(orderId, sku, quantity, expiresAt);
        }
        return reservation;
    }

    @Override
    public void confirm(String orderId) {
        Order order = orderService.findById(Integer.getInteger(orderId));
        if(order != null) {
            order.getProductList()
                    .stream().allMatch(
                            product->productService.reduceProduct(product.getProductId(), product.getQuantity(), stockAlertListener
                    )
            );
        }
    }

    @Override
    public int available(String sku) {
        return productService.findBySku(sku).getQuantity();
    }
}

