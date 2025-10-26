package com.filazero.demo.notifications;

import org.springframework.stereotype.Component;

import com.filazero.demo.notifications.dtos.NotificationsResponseDTO;
import com.filazero.demo.typeNotifications.TypeNotificationsMapper;
import com.filazero.demo.typeNotifications.dtos.TypeNotificationsResponseDTO;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class NotificationMapper {

    private final TypeNotificationsMapper typeNotificationMapper;

    public NotificationsResponseDTO toResponseDTO(NotificationsEntity entity) {
        if (entity == null) {
            return null;
        }

        // // Variable local para evitar el warning
        // var typeNotification = entity.getTypeNotification();
        // var typeNotificationDTO = typeNotification != null 
        //         ? typeNotificationsMapper.toResponseDTO(typeNotification) 
        //         : null;

        TypeNotificationsResponseDTO typeNotificationsDTO;
        return new NotificationsResponseDTO(
                entity.getId(),
                entity.getCustomer().getId(),
                entity.getDelivery() != null ? entity.getDelivery().getId() : null,
                typeNotificationsDTO,
                entity.getDatetime(),
                entity.isRead()
        );
    }
}