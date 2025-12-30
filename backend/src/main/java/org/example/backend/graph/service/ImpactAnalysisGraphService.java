package org.example.backend.graph.service;

import org.example.backend.graph.model.ImpactedProduct;

import java.util.List;

public interface ImpactAnalysisGraphService {

    List<ImpactedProduct> findImpactedProducts(Long supplierId);
}
