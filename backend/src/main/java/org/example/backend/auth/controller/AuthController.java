package org.example.backend.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.auth.dto.LoginRequest;
import org.example.backend.auth.dto.LoginResponse;
import org.example.backend.auth.dto.SignupRequest;
import org.example.backend.auth.dto.SignupResponse;
import org.example.backend.auth.entity.User;
import org.example.backend.auth.entity.enums.UserRole;
import org.example.backend.auth.service.UserService;
import org.example.backend.common.response.ApiResponse;
import org.example.backend.security.jwt.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


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
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        User user = userService.authenticate(
                request.getEmail(),
                request.getPassword()
        );

        String token = jwtTokenProvider.generateToken(user);

        return ApiResponse.success(new LoginResponse(token));
    }

}
