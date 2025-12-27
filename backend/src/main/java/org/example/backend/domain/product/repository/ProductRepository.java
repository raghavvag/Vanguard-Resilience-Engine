package org.example.backend.domain.product.repository;

import org.example.backend.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product>findBySupplierId(Long supplierId);
    boolean existsBySku(String sku);
}
