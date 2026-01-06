package org.example.backend.ai.controller;

import org.example.backend.ai.model.AIContext;
import org.example.backend.ai.prompt.PromptBuilder;
import org.example.backend.ai.service.AIReasoningService;
import org.example.backend.ai.service.ContextAssembler;
import org.example.backend.common.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AIAnalysisController {

    private final ContextAssembler assembler;
    private final AIReasoningService aiService;

    public AIAnalysisController(
            ContextAssembler assembler,
            AIReasoningService aiService) {
        this.assembler = assembler;
        this.aiService = aiService;
    }

    @PostMapping("/impact")
    public ApiResponse<String> analyzeImpact(
            @RequestParam Long supplierId,
            @RequestParam String question) {

        AIContext context =
                assembler.assemble(supplierId, question);

        String prompt =
                PromptBuilder.build(context);

        return ApiResponse.success(
                aiService.analyze(prompt)
        );
    }
}
