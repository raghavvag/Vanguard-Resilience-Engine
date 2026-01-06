package org.example.backend.ai.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class ExternalEventSearchClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String SEARCH_URL =
            "http://localhost:8000/search/external-events?query={query}";

    public List<Map<String, Object>> searchRelevantEvents(String query) {

        return restTemplate.getForObject(
                SEARCH_URL,
                List.class,
                query
        );
    }
}
