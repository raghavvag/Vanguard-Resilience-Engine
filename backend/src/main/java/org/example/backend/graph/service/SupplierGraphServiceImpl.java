package org.example.backend.graph.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.ingestion.event.SupplierCreatedEvent;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierGraphServiceImpl implements SupplierGraphService{
    private final Neo4jClient neo4jClient;


    @Override
    public void createSupplierNode(SupplierCreatedEvent event) {
        neo4jClient.query("""
                            MERGE (s:Supplier {id: $id})
                            SET s.name = $name,
                                s.country = $country
                
                """)
                .bind(event.getSupplierId()).to("id")
                .bind(event.getName()).to("name")
                .bind(event.getCountry()).to("country")
                .run();
    }
}
