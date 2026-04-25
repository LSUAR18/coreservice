package com.health.api.core.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record MedicationDTO(UUID id, String name, UUID presentationId) {}