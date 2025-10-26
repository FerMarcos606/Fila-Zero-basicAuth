package com.filazero.demo.turns.dtos;

import java.time.LocalDateTime;
import com.filazero.demo.enums.TurnsStatus;

public record TurnsResponseDTO(
    Long id,
    String code,
    LocalDateTime startTime,
    LocalDateTime endTime,
    LocalDateTime timeSlot,
    String status
) {}

