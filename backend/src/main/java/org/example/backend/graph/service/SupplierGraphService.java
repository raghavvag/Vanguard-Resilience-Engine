package org.example.backend.graph.service;

import org.example.backend.ingestion.event.SupplierCreatedEvent;

public interface SupplierGraphService {

    void createSupplierNode(SupplierCreatedEvent event);
}
