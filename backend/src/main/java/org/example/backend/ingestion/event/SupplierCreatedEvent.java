package org.example.backend.ingestion.event;

import lombok.Getter;

@Getter
public class SupplierCreatedEvent extends DomainEvent {
    private final Long supplierId;
    private final String name;
    private final String country;

    public SupplierCreatedEvent(Long supplierId, String name, String country) {
        super("SUPPLIER_CREATED");
        this.supplierId = supplierId;
        this.name = name;
        this.country = country;
    }
}
