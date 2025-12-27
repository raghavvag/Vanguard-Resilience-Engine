package org.example.backend.domain.supplier.controller;

import org.example.backend.common.response.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin-only")
    public ApiResponse<String> adminEndpoint() {
        return ApiResponse.success("Admin access granted");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPS_MANAGER')")
    @GetMapping("/ops")
    public ApiResponse<String> opsEndpoint() {
        return ApiResponse.success("Ops access granted");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPS_MANAGER', 'SME_USER')")
    @GetMapping("/view")
    public ApiResponse<String> viewEndpoint() {
        return ApiResponse.success("View access granted");
    }
}
