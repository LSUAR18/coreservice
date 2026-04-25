package com.health.api.core.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PrescriptionRequest {
    private UUID patientId;
    private UUID medicationId;
    private String dosage;
    private String instructions;
    private Integer frequencyHours;
    private LocalDate startDate;
    private LocalDate endDate;
}