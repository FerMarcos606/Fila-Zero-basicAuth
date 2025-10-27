package com.filazero.demo.delivery.dtos;

import java.util.List;

import com.filazero.demo.detailDelivery.dtos.DetailDeliveryRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DeliveryRequestDTO(
    
    @NotNull(message = "El customerId es obligatorio")
    Long customerId,

    Boolean paid,

    @NotEmpty(message = "Debe incluir al menos un producto")
    @Valid
    List<DetailDeliveryRequestDTO> details
) {}



    


