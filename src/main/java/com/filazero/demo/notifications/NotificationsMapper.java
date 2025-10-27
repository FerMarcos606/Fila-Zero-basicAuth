package com.filazero.demo.notifications;

import org.springframework.stereotype.Component;

import com.filazero.demo.notifications.dtos.NotificationsResponseDTO;
import com.filazero.demo.typeNotifications.TypeNotificationsMapper;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class NotificationsMapper {

    private final TypeNotificationsMapper typeNotificationsMapper;

    public NotificationsResponseDTO toResponseDTO(NotificationsEntity entity) {
        if (entity == null) {
            return null;
        }

        // Obtener el tipo de notificación y convertirlo a DTO
        var typeNotification = entity.getTypeNotification();
        var typeNotificationsDTO = typeNotification != null 
                ? typeNotificationsMapper.toResponseDTO(typeNotification) 
                : null;

        return new NotificationsResponseDTO(
                entity.getId(),
                entity.getCustomer().getId(),
                entity.getDelivery() != null ? entity.getDelivery().getId() : null,
                typeNotificationsDTO, // ← Ahora SÍ tiene un valor asignado
                entity.getDatetime(),
                entity.isRead()
        );
    }
}