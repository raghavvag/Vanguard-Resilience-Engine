package org.example.backend.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupResponse {

    private final Long userId;
    private final String email;
    private final String role;
}
