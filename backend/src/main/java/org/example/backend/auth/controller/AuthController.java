package org.example.backend.auth.controller;

import jakarta.validation.Valid;
import org.example.backend.auth.dto.SignupRequest;
import org.example.backend.auth.dto.SignupResponse;
import org.example.backend.auth.entity.User;
import org.example.backend.auth.entity.enums.UserRole;
import org.example.backend.auth.service.UserService;
import org.example.backend.common.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ApiResponse<SignupResponse> signup(
            @Valid @RequestBody SignupRequest request) {

        User user = userService.createuser(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                UserRole.SME_USER
        );

        SignupResponse response = new SignupResponse(
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );

        return ApiResponse.success(response);
    }
}
