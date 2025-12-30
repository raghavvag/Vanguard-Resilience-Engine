package org.example.backend.graph.service;

import org.example.backend.ingestion.event.ProductCreatedEvent;

public interface ProductGraphService {

    void createProductNode(ProductCreatedEvent event);
}
