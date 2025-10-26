package com.filazero.demo.turns.dtos;

import java.time.LocalDateTime;


public record TurnsResponseDTO(
    Long id,
    String code,
    LocalDateTime startTime,
    LocalDateTime endTime,
    LocalDateTime timeSlot,
    String status
) {}

