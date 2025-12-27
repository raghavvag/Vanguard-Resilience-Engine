package org.example.backend.domain.product.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.backend.common.exception.BadRequestException;
import org.example.backend.common.exception.ResourceNotFoundException;
import org.example.backend.domain.product.entity.Product;
import org.example.backend.domain.product.repository.ProductRepository;
import org.example.backend.domain.supplier.entity.Supplier;
import org.example.backend.domain.supplier.repository.SupplierRespository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final SupplierRespository supplierRepository;

    @Override
    public Product create(String name, String sku, Long supplierId) {
        if (productRepository.existsBySku(sku)) {
            throw new BadRequestException("SKU already exists");
        }

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier not found"));

        Product product = new Product(name, sku, supplier);
        return productRepository.save(product);    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));    }

    @Override
    public List<Product> getBySupplier(Long supplierId) {
        return productRepository.findBySupplierId(supplierId);
    }

    @Override
    public void deactivate(Long id) {
        Product product = getById(id);
        product.deactivate();
    }
}
