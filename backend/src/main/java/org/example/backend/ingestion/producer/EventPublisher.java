package org.example.backend.ingestion.producer;

import org.example.backend.ingestion.event.DomainEvent;
import org.example.backend.ingestion.external.ExternalDisruptionEvent;

public interface EventPublisher {
    void publish(String topic, DomainEvent event);
    void publishexternal(String topic, ExternalDisruptionEvent event);
}
