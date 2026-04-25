package com.health.api.core.controllers;

import com.health.api.core.dto.ApiResponse;
import com.health.api.core.dto.UserResponse;
import com.health.api.core.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final AccountRepository accountRepository;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> getMyProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(ApiResponse.error(401, "Unauthorized"));
        }

        String email = authentication.getName();

        return accountRepository.findByEmail(email)
                .map(user -> {
                    UserResponse data = UserResponse.builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .profile(user.getProfile())
                            .specialty(user.getSpecialty())
                            .build();
                    return ResponseEntity.ok(ApiResponse.success(data, "Perfil recuperado"));
                })
                .orElse(ResponseEntity.status(404).body(ApiResponse.error(404, "Usuario no encontrado")));
    }
}