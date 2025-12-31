package org.example.backend.graph.service;

import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

@Service
public class RegionGraphServiceimpl implements RegionGraphService {

    private final Neo4jClient neo4jClient;

    public RegionGraphServiceimpl(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @Override
    public void linkSupplierToRegion(Long supplierId, String country) {

        neo4jClient.query("""
            MERGE (r:Region {name: $country})
            MATCH (s:Supplier {id: $supplierId})
            MERGE (r)-[:HAS_SUPPLIER]->(s)
        """)
                .bind(country).to("country")
                .bind(supplierId).to("supplierId")
                .run();
    }
}
