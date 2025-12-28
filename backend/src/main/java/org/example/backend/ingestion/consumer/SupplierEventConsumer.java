package org.example.backend.ingestion.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.backend.graph.service.SupplierGraphService;
import org.example.backend.ingestion.event.SupplierCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierEventConsumer {
    private final ObjectMapper objectMapper;
    private final SupplierGraphService supplierGraphService;


    @KafkaListener(topics = "supplier-events", groupId = "graph-projector")
    public void handleSupplierCreated(String message) {
        try {
            SupplierCreatedEvent event =
                    objectMapper.readValue(message, SupplierCreatedEvent.class);

            if ("SUPPLIER_CREATED".equals(event.getEventType())) {
                supplierGraphService.createSupplierNode(event);
            }

        } catch (Exception ex) {
            // later: DLQ, retry, logging
            throw new RuntimeException("Failed to process supplier event", ex);
        }
    }
}
