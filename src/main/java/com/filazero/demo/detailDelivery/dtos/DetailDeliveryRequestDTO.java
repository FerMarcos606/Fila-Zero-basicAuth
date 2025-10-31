package com.filazero.demo.detailDelivery.dtos;

import jakarta.validation.constraints.NotNull;

public record DetailDeliveryRequestDTO(
    String phoneNumber,  
    @NotNull Long productId,
    @NotNull Integer quantity
) {}

