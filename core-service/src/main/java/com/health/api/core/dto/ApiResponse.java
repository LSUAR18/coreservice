package com.health.api.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int statusCode;    // Ejemplo: 200, 201, 400
    private String statusDesc; // Ejemplo: "Operación exitosa", "Error de validación"
    private T data;            // El objeto real (UserResponse, AuthResponse, etc.)

    // Método de ayuda para éxitos rápidos
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .statusCode(200)
                .statusDesc(message)
                .data(data)
                .build();
    }

    // Método de ayuda para errores
    public static <T> ApiResponse<T> error(int code, String message) {
        return ApiResponse.<T>builder()
                .statusCode(code)
                .statusDesc(message)
                .data(null)
                .build();
    }
}