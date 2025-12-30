package org.example.backend.ingestion.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.ingestion.event.ProductCreatedEvent;
import org.example.backend.graph.service.ProductGraphService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventConsumer {

    private final ObjectMapper objectMapper;
    private final ProductGraphService productGraphService;

    public ProductEventConsumer(ObjectMapper objectMapper,
                                ProductGraphService productGraphService) {
        this.objectMapper = objectMapper;
        this.productGraphService = productGraphService;
    }

    @KafkaListener(topics = "product-events", groupId = "graph-projector")
    public void handleProductEvent(String message) {
        try {
            ProductCreatedEvent event =
                    objectMapper.readValue(message, ProductCreatedEvent.class);

            if ("PRODUCT_CREATED".equals(event.getEventType())) {
                productGraphService.createProductNode(event);
            }

        } catch (Exception ex) {
            throw new RuntimeException("Failed to process product event", ex);
        }
    }
}
