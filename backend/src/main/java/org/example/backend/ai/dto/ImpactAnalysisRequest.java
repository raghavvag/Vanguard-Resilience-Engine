package org.example.backend.ai.dto;

public class ImpactAnalysisRequest {

    private String question;
    private Long regionId;   // optional
    private Long supplierId; // optional

    public String getQuestion() {
        return question;
    }

    public Long getRegionId() {
        return regionId;
    }

    public Long getSupplierId() {
        return supplierId;
    }
}
