package com.filazero.demo.delivery.dtos;

import java.util.List;

import com.filazero.demo.detailDelivery.dtos.DetailDeliveryRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

// DeliveryRequestDTO
public record DeliveryRequestDTO(
    
    @NotNull(message = "El customerId es obligatorio")
    Long customerId,
    Long turnId,
    List<DeliveryDetailRequestDTO> details,

    @NotBlank(message = "El teléfono es obligatorio")
    String phoneNumber, 

    Boolean paid,

    @NotEmpty(message = "Debe incluir al menos un producto")
    @Valid
    List<DetailDeliveryRequestDTO> details
) {}





    


