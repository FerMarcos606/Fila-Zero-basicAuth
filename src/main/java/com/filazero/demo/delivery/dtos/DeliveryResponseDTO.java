package com.filazero.demo.delivery.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.filazero.demo.detailDelivery.dtos.DeliveryDetailResponseDTO;
import com.filazero.demo.enums.DeliveryStatus;
import com.filazero.demo.nofications.dtos.NotificationsResponseDTO;

public record DeliveryResponseDTO(
    Long id,
    Long customerId,
    Long turnId,
    List<DeliveryDetailResponseDTO> details,
    BigDecimal total,
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
