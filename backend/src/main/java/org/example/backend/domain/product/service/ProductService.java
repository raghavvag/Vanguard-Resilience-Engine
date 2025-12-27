package org.example.backend.domain.product.service;

import org.example.backend.domain.product.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(String name,String sku,Long supplierId);
    Product getById(Long id);

    List<Product> getBySupplier(Long supplierId);

    void deactivate(Long id);
}
