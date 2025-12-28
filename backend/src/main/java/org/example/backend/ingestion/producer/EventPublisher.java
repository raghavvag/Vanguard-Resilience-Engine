package org.example.backend.ingestion.producer;

import org.example.backend.ingestion.event.DomainEvent;

public interface EventPublisher {
    void publish(String topic, DomainEvent event);
}
