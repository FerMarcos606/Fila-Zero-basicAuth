package com.filazero.demo.notifications.dtos;

import java.time.LocalDateTime;
import com.filazero.demo.typeNotifications.dtos.TypeNotificationsResponseDTO;

public record NotificationsResponseDTO(

    Long id,
    
    Long customerId,
    
    Long deliveryId, // Puede ser null
    
    TypeNotificationsResponseDTO typeNotifications,
    
    LocalDateTime datetime,
    
    boolean isRead


) {}
