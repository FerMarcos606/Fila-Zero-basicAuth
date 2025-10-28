package com.filazero.demo.detailDelivery.dtos;

public record DeliveryDetailRequestDTO(
    Long productId,
    Integer quantity
) {}
