package com.filazero.demo.delivery;

import com.filazero.demo.delivery.dtos.DeliveryRequestDTO;
import com.filazero.demo.delivery.dtos.DeliveryResponseDTO;
import com.filazero.demo.profile.ProfileEntity;

public class DeliveryMapper {

    public static DeliveryEntity toEntity(DeliveryRequestDTO dtoRequest) {
        DeliveryEntity delivery = new DeliveryEntity();

        delivery.setPaid(dtoRequest.paid());
        delivery.setCreatedAt(dtoRequest.createdAt());
        delivery.setAssignedSlot(dtoRequest.assignedSlot());
        delivery.setRescheduledSlot(dtoRequest.rescheduledSlot());
        delivery.setWasRescheduled(dtoRequest.wasRescheduled());
        delivery.setQueuePosition(dtoRequest.queuePosition());
        delivery.setCancelableUntil(dtoRequest.cancelableUntil());
        delivery.setThankYouMessage(dtoRequest.thankYouMessage());

        return delivery;
    }

    public static DeliveryResponseDTO toDTO(DeliveryEntity entity) {
        return new DeliveryResponseDTO(
            entity.getId(),
            entity.getCustomer() != null ? entity.getCustomer().getId() : null,
            entity.getTurn() != null ? entity.getTurn().getId() : null,
            null, // detalles, podés mapearlos si tenés DeliveryDetailDTO
            entity.getStatus(),
            entity.getPaid(),
            entity.getCreatedAt(),
            entity.getAssignedSlot(),
            entity.getRescheduledSlot(),
            entity.getWasRescheduled(),
            entity.getQueuePosition(),
            entity.getCancelableUntil(),
            entity.getThankYouMessage(),
            null // notifications, podés mapearlos si tenés NotificationDTO
        );
    }
}

