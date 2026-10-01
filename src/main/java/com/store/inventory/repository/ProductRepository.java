package com.store.inventory.repository;

import com.store.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();
    Optional<Product> findById(Integer id);
    @Query(
            """
            SELECT p
            FROM Product p
            WHERE p.quantity <= 5
            """)
    List<Product> findByQuantity(Integer quantity);
    Optional<Product> findBySku(String sku);
}
