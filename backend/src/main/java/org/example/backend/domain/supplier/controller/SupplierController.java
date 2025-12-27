package org.example.backend.domain.supplier.controller;

import org.example.backend.common.response.ApiResponse;
import org.example.backend.domain.supplier.entity.Supplier;
import org.example.backend.domain.supplier.service.SupplierService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPS_MANAGER')")
    @PostMapping
    public ApiResponse<Supplier> createSupplier(
            @RequestParam String name,
            @RequestParam String country) {

        return ApiResponse.success(
                supplierService.create(name, country)
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPS_MANAGER', 'SME_USER')")
    @GetMapping("/{id}")
    public ApiResponse<Supplier> getSupplier(@PathVariable Long id) {
        return ApiResponse.success(
                supplierService.getById(id)
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPS_MANAGER')")
    @GetMapping
    public ApiResponse<List<Supplier>> getAllSuppliers() {
        return ApiResponse.success(
                supplierService.getAll()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deactivateSupplier(@PathVariable Long id) {
        supplierService.deactivate(id);
        return ApiResponse.success(null);
    }
}
