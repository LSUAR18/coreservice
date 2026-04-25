package com.health.api.core.dto;

import lombok.*;
import java.util.UUID;

@Data
@Builder
public class PatientResponse {
    private UUID id;
    private String fullName; // Combinación de first + last name
    private String documentNumber;
    private String phone;
    private Boolean status;
    private String syncCode;
    private String email;
}