package org.example.backend.ingestion.event;

public class ProductCreatedEvent extends DomainEvent {

    private final Long productId;
    private final String name;
    private final String sku;
    private final Long supplierId;

    public ProductCreatedEvent(
            Long productId,
            String name,
            String sku,
            Long supplierId
    ) {
        super("PRODUCT_CREATED");
        this.productId = productId;
        this.name = name;
        this.sku = sku;
        this.supplierId = supplierId;
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

    public Long getSupplierId() {
        return supplierId;
    }
}
