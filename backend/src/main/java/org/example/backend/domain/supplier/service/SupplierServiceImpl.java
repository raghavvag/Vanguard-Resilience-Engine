package org.example.backend.domain.supplier.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.backend.common.exception.ResourceNotFoundException;
import org.example.backend.domain.supplier.entity.Supplier;
import org.example.backend.domain.supplier.repository.SupplierRepository;
import org.example.backend.ingestion.event.SupplierCreatedEvent;
import org.example.backend.ingestion.producer.EventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService{
    private final SupplierRepository supplierRespository;
    private final EventPublisher eventPublisher;


    @Override
    public Supplier create(String name, String country) {
        Supplier supplier = supplierRespository.save(
                new Supplier(name, country)
        );

        eventPublisher.publish(
                "supplier-events",
                new SupplierCreatedEvent(
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getCountry()
                )
        );

        return supplier;

    }

    @Override
    public Supplier getById(Long id) {
        return supplierRespository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Supplier not found"));

    }

    @Override
    public List<Supplier> getAll() {
        return supplierRespository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        Supplier supplier=getById(id);
        supplier.deactivate();
    }
}
