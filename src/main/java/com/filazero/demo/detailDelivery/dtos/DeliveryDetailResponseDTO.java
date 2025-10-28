package com.filazero.demo.detailDelivery.dtos;

import java.math.BigDecimal;

public record DeliveryDetailResponseDTO(

 Long productId,
    String productName,
    Integer quantity,
    BigDecimal unitPrice,
    BigDecimal subtotal
) {}
