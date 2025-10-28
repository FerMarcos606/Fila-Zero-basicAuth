package com.filazero.demo.delivery.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.filazero.demo.detailDelivery.dtos.DeliveryDetailRequestDTO;

public record DeliveryRequestDTO(
    
    Long customerId,
    Long turnId,
    List<DeliveryDetailRequestDTO> details,
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



    


