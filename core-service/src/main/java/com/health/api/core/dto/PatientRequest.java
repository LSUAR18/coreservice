package com.health.api.core.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
public class PatientRequest {
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private LocalDate birthDate;
    private String phone;
    private String timezone;
    private String syncCode;
    private String email;
}