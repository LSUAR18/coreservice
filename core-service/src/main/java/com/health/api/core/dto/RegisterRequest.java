package com.health.api.core.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;
    
    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;
    
    @Email(message = "Email inválido")
    @NotBlank
    private String email;
    
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
    
    private String documentType;
    private String documentNumber;
    private String specialty;
    private String profile;
}