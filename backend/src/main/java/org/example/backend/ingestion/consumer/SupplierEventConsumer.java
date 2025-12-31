package org.example.backend.ingestion.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.backend.graph.service.RegionGraphService;
import org.example.backend.graph.service.SupplierGraphService;
import org.example.backend.ingestion.event.SupplierCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SupplierEventConsumer {

    private final ObjectMapper objectMapper;
    private final SupplierGraphService supplierGraphService;
    private final RegionGraphService regionGraphService;

    public SupplierEventConsumer(
            ObjectMapper objectMapper,
            SupplierGraphService supplierGraphService,
            RegionGraphService regionGraphService) {

        this.objectMapper = objectMapper;
        this.supplierGraphService = supplierGraphService;
        this.regionGraphService = regionGraphService;
    }

    @KafkaListener(topics = "supplier-events", groupId = "graph-projector")
    public void handleSupplierEvent(String message) {

        try {
            SupplierCreatedEvent event =
                    objectMapper.readValue(message, SupplierCreatedEvent.class);

            if ("SUPPLIER_CREATED".equals(event.getEventType())) {

                supplierGraphService.createSupplierNode(event);

                regionGraphService.linkSupplierToRegion(
                        event.getSupplierId(),
                        event.getCountry()
                );
            }

        } catch (Exception ex) {
            throw new RuntimeException("Failed to process supplier event", ex);
        }
    }
}
