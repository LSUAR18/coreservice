package com.health.api.core.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record PatientDTO(UUID id, String firstName, String lastName, String documentNumber, String phone, String syncCode, UUID municipalityId, String email) {}