package com.health.api.core.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "presentations") // Nombre exacto de la tabla en el MER
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name; // Ej: Tabletas, Jarabe, etc.
}