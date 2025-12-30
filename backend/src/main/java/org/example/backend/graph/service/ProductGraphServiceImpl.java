package org.example.backend.graph.service;

import org.example.backend.ingestion.event.ProductCreatedEvent;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

@Service
public class ProductGraphServiceImpl implements ProductGraphService {

    private final Neo4jClient neo4jClient;

    public ProductGraphServiceImpl(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @Override
    public void createProductNode(ProductCreatedEvent event) {

        neo4jClient.query("""
            MATCH (s:Supplier {id: $supplierId})
            MERGE (p:Product {id: $productId})
            SET p.name = $name,
                p.sku = $sku
            MERGE (s)-[:SUPPLIES]->(p)
        """)
                .bind(event.getSupplierId()).to("supplierId")
                .bind(event.getProductId()).to("productId")
                .bind(event.getName()).to("name")
                .bind(event.getSku()).to("sku")
                .run();
    }
}
