package com.filazero.demo.notifications;

import com.filazero.nofications.dtos.NotificationsResponseDTO;
import com.filazero.typeNotifications.TypeNotificationsMapper;
import com.filazero.notifications.NotificationsEntity;
import com.filazero.typeNotifications.TypeNotificationsMapper; 
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationsMapper {

    private final TypeNotificationsMapper typeNotificationMapper; // Inyectás el mapper correspondiente

    public NotificationsResponseDTO toResponseDTO(NotificationsEntity entity) {
        if (entity == null) {
            return null;
        }

        return NotificationsResponseDTO.builder()
                .id(entity.getId())
                .customerId(entity.getCustomer().getId())
                .deliveryId(entity.getDelivery() != null ? entity.getDelivery().getId() : null)
                .typeNotification(typeNotificationsMapper.toResponseDTO(entity.getTypeNotification())) // Delegás la conversión
                .datetime(entity.getDatetime())
                .isRead(entity.isRead())
                .build();
    }
}
