package com.health.api.core.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record AccountDTO(UUID id, String firstName, String lastName, String email, String specialty, String profile, String status) {}