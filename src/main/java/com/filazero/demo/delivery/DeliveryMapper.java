package com.filazero.demo.delivery;
import com.filazero.demo.delivery.dtos.DeliveryRequestDTO;
import com.filazero.demo.delivery.dtos.DeliveryResponseDTO;
import java.time.LocalDateTime;


public class DeliveryMapper {

    public static DeliveryEntity toEntity(DeliveryRequestDTO dtoRequest) {
        DeliveryEntity delivery = new DeliveryEntity();

        delivery.setPaid(dtoRequest.paid());
        delivery.setCreatedAt(LocalDateTime.now());
        
        return delivery;
    }

    public static DeliveryResponseDTO toDTO(DeliveryEntity entity) {
        return new DeliveryResponseDTO(
            entity.getId(),
            entity.getCustomer() != null ? entity.getCustomer().getId() : null,
            entity.getTurn() != null ? entity.getTurn().getId() : null,
            null, 
            entity.getStatus(),
            entity.getPaid(),
            entity.getCreatedAt(),
            entity.getAssignedSlot(),
            entity.getRescheduledSlot(),
            entity.getWasRescheduled(),
            entity.getQueuePosition(),
            entity.getCancelableUntil(),
            entity.getThankYouMessage(),
            null 
        );
    }
}

