package org.example.backend.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIReasoningService {

    private final ChatClient chatClient;

    public AIReasoningService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String analyze(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
