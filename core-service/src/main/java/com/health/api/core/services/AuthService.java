package com.health.api.core.services;

import com.health.api.core.dto.*;
import com.health.api.core.entities.Account;
import com.health.api.core.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // 1. Importante para el logger
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public MessageResponse register(RegisterRequest request) {
        log.info("Iniciando proceso de registro para el email: {}", request.getEmail());

        try {
            // 3. Mapeo completo de todos los campos del MER
            var user = Account.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail().toLowerCase())
                    .passwordHash(passwordEncoder.encode(request.getPassword()))
                    .documentType(request.getDocumentType())
                    .documentNumber(request.getDocumentNumber())
                    .specialty(request.getSpecialty())
                    .profile(request.getProfile())
                    .status("ACTIVE")
                    .build();

            log.debug("Guardando entidad Account en Supabase...");
            repository.save(user);
            log.info("Usuario guardado exitosamente con ID: {}", user.getId());

            return MessageResponse.builder()
                    .message("Usuario registrado exitosamente")
                    .build();

        } catch (Exception e) {
            log.error("Error crítico durante el registro de {}: {}", request.getEmail(), e.getMessage());
            throw e; // Lanza el error para que el GlobalExceptionHandler lo capture
        }
    }

    public AuthResponse authenticate(AuthRequest request) {
        log.info("Intento de login para el usuario: {}", request.email());
        
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        var user = repository.findByEmail(request.email())
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado tras autenticación: {}", request.email());
                    return new RuntimeException("Usuario no encontrado");
                });

        log.info("Login exitoso para: {}", request.email());
        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .profile(user.getProfile())
                .build();
    }
}