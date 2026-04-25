package com.health.api.core.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;

@Builder
public record PrescriptionDTO(
    UUID id, UUID patientId, UUID medicationId, 
    String dosage, String instructions, Integer frequencyHours,
    LocalDate startDate, LocalDate endDate, Boolean isActive
) {}
