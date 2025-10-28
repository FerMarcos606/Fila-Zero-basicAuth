package com.filazero.demo.delivery.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.filazero.demo.detailDelivery.dtos.DetailDeliveryRequestDTO;

// DeliveryRequestDTO
public record DeliveryRequestDTO(
    String phoneNumber,
    Long turnId,
    Boolean paid,
    LocalDateTime assignedSlot,
    LocalDateTime rescheduledSlot,
    Boolean wasRescheduled,
    Integer queuePosition,
    LocalDateTime cancelableUntil,
    String thankYouMessage,
    List<DetailDeliveryRequestDTO> details 
) {}




    


