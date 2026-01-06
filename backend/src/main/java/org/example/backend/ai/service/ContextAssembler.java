package org.example.backend.ai.service;

import org.example.backend.ai.client.ExternalEventSearchClient;
import org.example.backend.ai.model.AIContext;
import org.example.backend.graph.model.ImpactedProduct;
import org.example.backend.graph.service.ImpactAnalysisGraphService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContextAssembler {

    private final ImpactAnalysisGraphService graphService;
    private final ExternalEventSearchClient eventClient;

    public ContextAssembler(
            ImpactAnalysisGraphService graphService,
            ExternalEventSearchClient eventClient) {
        this.graphService = graphService;
        this.eventClient = eventClient;
    }

    public AIContext assemble(Long supplierId, String question) {

        List<ImpactedProduct> products =
                graphService.findImpactedProducts(supplierId);

        List<Map<String, Object>> events =
                eventClient.searchRelevantEvents(question);

        return new AIContext(products, events);
    }
}
