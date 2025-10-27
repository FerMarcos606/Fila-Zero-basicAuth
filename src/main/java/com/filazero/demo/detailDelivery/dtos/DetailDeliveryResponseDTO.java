package com.filazero.demo.detailDelivery.dtos;

import java.math.BigDecimal;

public record DetailDeliveryResponseDTO(
    Long id,
    Long deliveryId,
    Long productId,
    String productName, 
    String productDescription,
    Integer quantity,
    BigDecimal unitPrice,
    BigDecimal subtotal
) {}


