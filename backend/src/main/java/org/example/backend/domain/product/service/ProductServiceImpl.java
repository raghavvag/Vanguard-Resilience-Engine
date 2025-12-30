package org.example.backend.domain.product.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.backend.common.exception.BadRequestException;
import org.example.backend.common.exception.ResourceNotFoundException;
import org.example.backend.domain.product.entity.Product;
import org.example.backend.domain.product.repository.ProductRepository;
import org.example.backend.domain.supplier.entity.Supplier;
import org.example.backend.domain.supplier.repository.SupplierRepository;
import org.example.backend.ingestion.event.ProductCreatedEvent;
import org.example.backend.ingestion.producer.EventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final EventPublisher eventPublisher;
    @Override
    public Product create(String name, String sku, Long supplierId) {

        if (productRepository.existsBySku(sku)) {
            throw new BadRequestException("SKU already exists");
        }

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier not found"));

        Product product = productRepository.save(
                new Product(name, sku, supplier)
        );

        eventPublisher.publish(
                "product-events",
                new ProductCreatedEvent(
                        product.getId(),
                        product.getName(),
                        product.getSku(),
                        supplier.getId()
                )
        );

        return product;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getBySupplier(Long supplierId) {
        return productRepository.findBySupplierId(supplierId);
    }

    @Override
    public void deactivate(Long id) {
        Product product = getById(id);
        product.deactivate();
        // (later: PRODUCT_DEACTIVATED event)
    }
}
