package org.example.backend.domain.product.controller;

import org.example.backend.common.response.ApiResponse;
import org.example.backend.domain.product.entity.Product;
import org.example.backend.domain.product.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPS_MANAGER')")
    @PostMapping
    public ApiResponse<Product> createProduct(
            @RequestParam String name,
            @RequestParam String sku,
            @RequestParam Long supplierId) {

        return ApiResponse.success(
                productService.create(name, sku, supplierId)
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPS_MANAGER', 'SME_USER')")
    @GetMapping("/{id}")
    public ApiResponse<Product> getProduct(@PathVariable Long id) {
        return ApiResponse.success(productService.getById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPS_MANAGER')")
    @GetMapping("/by-supplier/{supplierId}")
    public ApiResponse<List<Product>> getProductsBySupplier(
            @PathVariable Long supplierId) {

        return ApiResponse.success(
                productService.getBySupplier(supplierId)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deactivateProduct(@PathVariable Long id) {
        productService.deactivate(id);
        return ApiResponse.success(null);
    }
}
