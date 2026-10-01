package com.store.inventory.dto;

import com.store.inventory.api.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer productId;
    private String sku;
    private Integer quantity;
    private ProductCategory productCategory;
}
