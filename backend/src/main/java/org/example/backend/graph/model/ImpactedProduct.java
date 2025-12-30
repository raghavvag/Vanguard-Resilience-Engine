package org.example.backend.graph.model;

public class ImpactedProduct {

    private final Long productId;
    private final String name;
    private final String sku;

    public ImpactedProduct(Long productId, String name, String sku) {
        this.productId = productId;
        this.name = name;
        this.sku = sku;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }
}
