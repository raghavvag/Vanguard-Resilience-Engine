package org.example.backend.domain.supplier.service;

import org.example.backend.domain.supplier.entity.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier create(String name, String country);

    Supplier getById(Long id);

    List<Supplier> getAll();

    void deactivate(Long id);
}
