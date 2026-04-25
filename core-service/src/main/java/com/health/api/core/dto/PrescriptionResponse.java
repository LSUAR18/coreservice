package com.health.api.core.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class PrescriptionResponse {
    private UUID id;
    private String medicationName;
    private String dosage;
    private Integer frequencyHours;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
}