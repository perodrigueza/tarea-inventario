package com.store.inventory.service.impl;

import com.store.inventory.api.StockAlertListener;
import com.store.inventory.dto.ProductDTO;
import com.store.inventory.entity.Product;
import com.store.inventory.repository.ProductRepository;
import com.store.inventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final Integer LOWER_STOCK = 5;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByLowerStock() {
        return productRepository.findByQuantity(LOWER_STOCK);
    }

    @Override
    public Product findByProductId(Integer id) {
        return productRepository.getById(id);
    }

    @Override
    public Product saveProduct(ProductDTO productDto) {
        Product product = new Product();
        product.setSku(productDto.getSku());
        product.setQuantity(productDto.getQuantity());
        product.setProductCategory(productDto.getProductCategory());
        product = productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(ProductDTO productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setSku(productDto.getSku());
        product.setQuantity(productDto.getQuantity());
        product.setProductCategory(productDto.getProductCategory());
        product = productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product findBySku(String sku) {
        return productRepository.findBySku(sku).orElseThrow(UnsupportedOperationException::new);
    }

    @Override
    public Boolean reduceProduct(Integer id, Integer quantity, StockAlertListener stockAlertListener) {
        Product product = findByProductId(id);
        if(product != null) {
            Integer quantityReduced  = product.getQuantity() - quantity;
            product.setQuantity(quantityReduced);
            if(quantityReduced <= LOWER_STOCK)
                stockAlertListener.onLowStock(product.getSku(), quantityReduced);
            return true;
        }
        return false;
    }
}
