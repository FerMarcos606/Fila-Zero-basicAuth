package com.filazero.demo.turns;

import com.filazero.demo.implementations.IService;
import com.filazero.demo.turns.dtos.TurnsResponseDTO;

import java.util.List;

public interface ITurnsService extends IService<TurnsResponseDTO, Void> {

    // Método específico opcional para asignar un turno cuando un pedido se paga
    TurnsResponseDTO assignTurnForPaidOrder(Long deliveryId);

    List<TurnsResponseDTO> getUpcomingTurns();
}

