package com.filazero.demo.turns;

import com.filazero.demo.turns.dtos.TurnsResponseDTO;

public class TurnsMapper {
    public static TurnsResponseDTO toDTO(TurnsEntity entity) {
        return new TurnsResponseDTO(
            entity.getId(),
            entity.getCode(),
            entity.getStartTime(),
            entity.getEndTime(),
            entity.getTimeSlot(),
            entity.getStatus() != null ? entity.getStatus().name() : null
        );
    }
}

