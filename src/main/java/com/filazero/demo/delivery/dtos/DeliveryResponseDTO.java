package com.filazero.demo.delivery.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.filazero.demo.enums.DeliveryStatus;

public record DeliveryResponseDTO(
    Long id,
    Long customerId,
    Long turnId,
    List<DetailDeliveryDTO> details,
    DeliveryStatus status,
    Boolean paid,
    LocalDateTime createdAt,
    LocalDateTime assignedSlot,
    LocalDateTime rescheduledSlot,
    Boolean wasRescheduled,
    Integer queuePosition,
    LocalDateTime cancelableUntil,
    String thankYouMessage,
    List<NotificationDTO> notifications
) {}
