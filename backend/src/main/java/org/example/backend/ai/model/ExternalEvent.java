package org.example.backend.ai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalEvent {
    private String id;
    private String title;
    private String description;
    private String source;
    private LocalDateTime timestamp;
    private Double relevanceScore;
}

