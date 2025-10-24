package com.filazero.demo.delivery.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record DeliveryRequestDTO(
    Long customerId,
    Long turnId,
    String phoneNumber,
    List<DetailDeliveryDTO> details,
    Boolean paid,
    LocalDateTime assignedSlot,
    LocalDateTime rescheduledSlot,
    Boolean wasRescheduled,
    Integer queuePosition,
    LocalDateTime cancelableUntil,
    String thankYouMessage
    
) {}

