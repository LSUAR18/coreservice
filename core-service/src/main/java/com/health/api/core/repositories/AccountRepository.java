package com.health.api.core.repositories;

import com.health.api.core.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    /**
     * Busca una cuenta por su correo electrónico. 
     * Fundamental para el proceso de Login y seguridad JWT.
     */
    Optional<Account> findByEmail(String email);
    
    /**
     * Verifica si ya existe un correo registrado para evitar duplicados.
     */
    Boolean existsByEmail(String email);
}