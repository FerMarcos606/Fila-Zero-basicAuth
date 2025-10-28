package com.filazero.demo.typeNotifications;

import org.springframework.stereotype.Component;
import com.filazero.demo.typeNotifications.dtos.TypeNotificationsResponseDTO;

@Component
public class TypeNotificationsMapper {

    public TypeNotificationsResponseDTO toResponseDTO(TypeNotificationsEntity entity) {
        if (entity == null) {
            return null;
        }

        return new TypeNotificationsResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getStatus()
        );
    }
}