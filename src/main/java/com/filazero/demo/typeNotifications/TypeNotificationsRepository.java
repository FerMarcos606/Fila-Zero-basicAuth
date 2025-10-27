package com.filazero.demo.typeNotifications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeNotificationsRepository extends JpaRepository<TypeNotificationsEntity, Long> {
    
    // Buscar por nombre (útil cuando crees notificaciones automáticamente en fase 2)
    Optional<TypeNotificationsEntity> findByName(String name);
}