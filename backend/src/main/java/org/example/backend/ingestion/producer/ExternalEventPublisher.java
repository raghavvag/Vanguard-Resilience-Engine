package org.example.backend.ingestion.producer;

import org.example.backend.ingestion.external.ExternalDisruptionEvent;
import org.springframework.stereotype.Component;

@Component
public class ExternalEventPublisher {

    private final EventPublisher eventPublisher;

    public ExternalEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishExternalEvent(ExternalDisruptionEvent event) {
        eventPublisher.publishexternal("external-events", event);
    }
}
