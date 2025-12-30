package org.example.backend.graph.service;

import org.example.backend.graph.model.ImpactedProduct;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpactAnalysisGraphServiceImpl implements ImpactAnalysisGraphService {

    private final Neo4jClient neo4jClient;

    public ImpactAnalysisGraphServiceImpl(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @Override
    public List<ImpactedProduct> findImpactedProducts(Long supplierId) {

        return (List<ImpactedProduct>) neo4jClient.query("""
            MATCH (s:Supplier {id: $supplierId})-[:SUPPLIES]->(p:Product)
            RETURN p.id AS productId, p.name AS name, p.sku AS sku
        """)
                .bind(supplierId).to("supplierId")
                .fetchAs(ImpactedProduct.class)
                .mappedBy((typeSystem, record) ->
                        new ImpactedProduct(
                                record.get("productId").asLong(),
                                record.get("name").asString(),
                                record.get("sku").asString()
                        )
                )
                .all();
    }
}
