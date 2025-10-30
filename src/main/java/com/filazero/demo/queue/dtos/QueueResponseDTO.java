package com.filazero.demo.queue.dtos;

import java.time.LocalDateTime;

public record QueueResponseDTO(
        Long id,
        Integer position,
        LocalDateTime joinedAt,
        Boolean active,
        Long deliveryId
) {}

