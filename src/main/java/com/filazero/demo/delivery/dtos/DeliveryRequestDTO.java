package com.filazero.demo.delivery.dtos;

import java.time.LocalDateTime;

public record DeliveryRequestDTO(
    
    Long customerId,
    Long turnId,
    Boolean paid,
    LocalDateTime createdAt,
    LocalDateTime assignedSlot,
    LocalDateTime rescheduledSlot,
    Boolean wasRescheduled,
    Integer queuePosition,
    LocalDateTime cancelableUntil,
    String thankYouMessage,
    String phoneNumber
) {}



    


