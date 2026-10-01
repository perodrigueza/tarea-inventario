package com.store.inventory.dto;

import com.store.inventory.api.ProductCategory;
import com.store.inventory.entity.Product;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer orderId;
    private LocalDate purchaseDate;
    private Integer orderStatus;
    private List<Product> productList;
}
