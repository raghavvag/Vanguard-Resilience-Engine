package org.example.backend.ai.model;

import org.example.backend.graph.model.ImpactedProduct;

import java.util.List;
import java.util.Map;

public class AIContext {

    private final List<ImpactedProduct> products;
    private final List<Map<String, Object>> externalEvents;

    public AIContext(
            List<ImpactedProduct> products,
            List<Map<String, Object>> externalEvents) {
        this.products = products;
        this.externalEvents = externalEvents;
    }

    public List<ImpactedProduct> getProducts() {
        return products;
    }

    public List<Map<String, Object>> getExternalEvents() {
        return externalEvents;
    }
}
