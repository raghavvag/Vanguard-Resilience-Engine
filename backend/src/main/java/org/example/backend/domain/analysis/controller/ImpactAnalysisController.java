package org.example.backend.domain.analysis.controller;

import org.example.backend.common.response.ApiResponse;
import org.example.backend.graph.model.ImpactedProduct;
import org.example.backend.graph.service.ImpactAnalysisGraphService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analysis")
public class ImpactAnalysisController {

    private final ImpactAnalysisGraphService impactService;

    public ImpactAnalysisController(ImpactAnalysisGraphService impactService) {
        this.impactService = impactService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPS_MANAGER', 'SME_USER')")
    @GetMapping("/supplier/{supplierId}/products")
    public ApiResponse<List<ImpactedProduct>> impactedProducts(
            @PathVariable Long supplierId) {

        return ApiResponse.success(
                impactService.findImpactedProducts(supplierId)
        );
    }
}
