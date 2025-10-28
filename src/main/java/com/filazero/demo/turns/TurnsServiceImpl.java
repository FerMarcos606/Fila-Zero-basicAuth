package com.filazero.demo.turns;

import java.util.List;

import org.springframework.stereotype.Service;

import com.filazero.demo.delivery.DeliveryEntity;
import com.filazero.demo.delivery.DeliveryRepository;
import com.filazero.demo.turns.dtos.TurnsResponseDTO;

@Service
public class TurnsServiceImpl implements ITurnsService {

    private final TurnsRepository turnsRepository;
    private final DeliveryRepository deliveryRepository;

    public TurnsServiceImpl(TurnsRepository turnsRepository, DeliveryRepository deliveryRepository) {
        this.turnsRepository = turnsRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public TurnsResponseDTO createEntity(Void unused) {
        throw new UnsupportedOperationException("Los turnos se generan automáticamente al pagar el pedido");
    }

    @Override
    public TurnsResponseDTO getByID(Long id) {
        return turnsRepository.findById(id)
                .map(TurnsMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado con id: " + id));
    }

    @Override
    public TurnsResponseDTO updateEntity(Long id, Void unused) {
        throw new UnsupportedOperationException("Los turnos no se actualizan manualmente");
    }

    @Override
    public void deleteEntity(Long id) {
        turnsRepository.deleteById(id);
    }

    @Override
    public List<TurnsResponseDTO> getEntities() {
        return turnsRepository.findAll().stream()
                .map(TurnsMapper::toDTO)
                .toList();
    }

    @Override
    public TurnsResponseDTO assignTurnForPaidOrder(Long deliveryId) {
        DeliveryEntity delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + deliveryId));

        // Lógica de asignación del turno según reglas de negocio
        TurnsEntity turno = new TurnsEntity();
        turno.setCode("TURN-" + delivery.getId());
        turno.setDelivery(delivery);
        turno.setTimeSlot(delivery.getCreatedAt().plusMinutes(10)); // ejemplo de margen de 10 min

        TurnsEntity saved = turnsRepository.save(turno);
        return TurnsMapper.toDTO(saved);
    }

    @Override
    public List<TurnsResponseDTO> getUpcomingTurns() {
        return turnsRepository.findAllByOrderByTimeSlotAsc().stream()
                .map(TurnsMapper::toDTO)
                .toList();
    }
}

