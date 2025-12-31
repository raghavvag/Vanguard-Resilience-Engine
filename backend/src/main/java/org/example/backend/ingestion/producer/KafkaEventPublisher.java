package org.example.backend.ingestion.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.backend.ingestion.event.DomainEvent;
import org.example.backend.ingestion.external.ExternalDisruptionEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventPublisher implements EventPublisher{
    private final KafkaTemplate<String,String>kafkaTemplate;
    private final ObjectMapper objectMapper;


    @Override
    public void publish(String topic, DomainEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(topic, payload);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to publish event", ex);
        }
    }

    @Override
    public void publishexternal(String topic, ExternalDisruptionEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(topic, payload);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to publish event", ex);
        }
    }
}

