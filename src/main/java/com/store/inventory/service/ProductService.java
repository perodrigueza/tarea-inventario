package com.store.inventory.service;

import com.store.inventory.api.StockAlertListener;
import com.store.inventory.dto.ProductDTO;
import com.store.inventory.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findByLowerStock();
    Product findByProductId(Integer id);
    Product saveProduct(ProductDTO productDto);
    Product updateProduct(ProductDTO productDto);
    void deleteProduct(Integer productId);
    Product findBySku(String sku);
    Boolean reduceProduct(Integer id, Integer quantity, StockAlertListener stockAlertListener);
}
