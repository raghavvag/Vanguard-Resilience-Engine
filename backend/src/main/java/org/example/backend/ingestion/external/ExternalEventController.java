package org.example.backend.ingestion.external;

import org.example.backend.common.response.ApiResponse;
import org.example.backend.ingestion.producer.ExternalEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/external-events")
public class ExternalEventController {

    private final ExternalEventPublisher publisher;

    public ExternalEventController(ExternalEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<Void> ingestEvent(
            @RequestParam String type,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String region
    ) {

        publisher.publishExternalEvent(
                new ExternalDisruptionEvent(
                        type,
                        title,
                        description,
                        region,
                        Instant.now()
                )
        );

        return ApiResponse.success(null);
    }
}
