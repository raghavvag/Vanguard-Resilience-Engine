package org.example.backend.ingestion.event;

import lombok.Getter;

import java.time.Instant;
@Getter

public class DomainEvent {
    private final String eventType;
    private final Instant occuredAt;
    protected DomainEvent(String eventType){
        this.eventType=eventType;
        this.occuredAt=Instant.now();
    }

}
