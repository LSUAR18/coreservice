package com.health.api.core.dto;

import lombok.Builder;

@Builder
public record AuthResponse(String token, String email, String firstName, String lastName, String profile) {}
