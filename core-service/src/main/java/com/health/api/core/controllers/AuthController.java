package com.health.api.core.controllers;

import com.health.api.core.dto.*;
import com.health.api.core.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<MessageResponse>> register(@RequestBody RegisterRequest request) {
        MessageResponse response = service.register(request);
        return ResponseEntity.ok(ApiResponse.success(response, "Registro completado con éxito"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> authenticate(@RequestBody AuthRequest request) {
        AuthResponse response = service.authenticate(request);
        return ResponseEntity.ok(ApiResponse.success(response, "Login exitoso"));
    }
}