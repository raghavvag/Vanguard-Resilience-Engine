package org.example.backend.ingestion.external;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
@Getter
@RequiredArgsConstructor
public class ExternalDisruptionEvent {

    private final String eventType;      // e.g. PORT_STRIKE
    private final String title;           // short headline
    private final String description;     // full text
    private final String region;          // country or city
    private final Instant occurredAt;


   }
