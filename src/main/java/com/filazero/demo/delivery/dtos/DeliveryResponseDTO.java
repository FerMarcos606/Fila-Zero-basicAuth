package com.filazero.demo.delivery.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.filazero.demo.detailDelivery.dtos.DetailDeliveryResponseDTO;
import com.filazero.demo.enums.DeliveryStatus;
import com.filazero.demo.notifications.dtos.NotificationsResponseDTO;

public record DeliveryResponseDTO(
    Long id,
    Long customerId,
    Long turnId,
    List<DetailDeliveryResponseDTO> details,
    DeliveryStatus status,
    Boolean paid,
    LocalDateTime createdAt,
    LocalDateTime assignedSlot,
    LocalDateTime rescheduledSlot,
    Boolean wasRescheduled,
    Integer queuePosition,
    LocalDateTime cancelableUntil,
    String thankYouMessage,
    List<NotificationsResponseDTO> notifications
) {}
