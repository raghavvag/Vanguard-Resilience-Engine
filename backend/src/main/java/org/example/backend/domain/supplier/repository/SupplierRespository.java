package org.example.backend.domain.supplier.repository;

import org.example.backend.domain.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRespository extends JpaRepository<Supplier,Long> {

}
