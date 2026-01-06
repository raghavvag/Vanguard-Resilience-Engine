package org.example.backend.ai.prompt;

import org.example.backend.ai.model.AIContext;

public class PromptBuilder {

    public static String build(AIContext context) {

        return """
        You are a supply chain risk analyst.

        Impacted Products:
        %s

        External Events:
        %s

        Tasks:
        1. Explain why these products are impacted
        2. Estimate severity (Low / Medium / High)
        3. Suggest mitigation actions

        Rules:
        - Do NOT invent facts
        - Use only provided context
        - Be concise and operational
        """.formatted(
                context.getProducts(),
                context.getExternalEvents()
        );
    }
}
