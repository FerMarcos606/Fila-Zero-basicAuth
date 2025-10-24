package com.filazero.demo.delivery;

import java.util.List;

import org.springframework.stereotype.Service;

import com.filazero.demo.customer.CustomerEntity;
import com.filazero.demo.customer.CustomerRepository;
import com.filazero.demo.delivery.dtos.DeliveryRequestDTO;
import com.filazero.demo.delivery.dtos.DeliveryResponseDTO;
import com.filazero.demo.turns.TurnsEntity;

@Service("deliveryService")
public class DeliveryServiceImpl implements InterfaceDeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final CustomerRepository customerRepository;
    private final TurnsRepository turnRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, CustomerRepository customerRepository, TurnRepository turnRepository) {
        this.deliveryRepository = deliveryRepository;
        this.customerRepository = customerRepository;
        this.turnsRepository = turnRepository;
    }

    @Override
    public List<DeliveryResponseDTO> getEntities() {
        return deliveryRepository.findAll().stream()
                .map(DeliveryMapper::toDTO)
                .toList();
    }

    @Override
    public DeliveryResponseDTO createEntity(DeliveryRequestDTO deliveryRequestDTO) {
        if (deliveryRequestDTO.phoneNumber() == null) {
            throw new RuntimeException("El número de teléfono no puede ser nulo");
        }

        CustomerEntity customer = customerRepository.findByUsername(deliveryRequestDTO.username())
    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con username: " + deliveryRequestDTO.username()));


        TurnsEntity turn = turnsRepository.findById(deliveryRequestDTO.turnId())
                .orElseThrow(() -> new RuntimeException("Turno no encontrado con id: " + deliveryRequestDTO.turnId()));

        DeliveryEntity delivery = DeliveryMapper.toEntity(deliveryRequestDTO);
        delivery.setCustomer(customer);
        delivery.setTurn(turn);

        DeliveryEntity saved = deliveryRepository.save(delivery);
        return DeliveryMapper.toDTO(saved);
    }

    @Override
    public DeliveryResponseDTO getByID(Long id) {
        DeliveryEntity delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada con id: " + id));
        return DeliveryMapper.toDTO(delivery);
    }

    @Override
    public DeliveryResponseDTO updateEntity(Long id, DeliveryRequestDTO deliveryRequestDTO) {
        DeliveryEntity delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada con id: " + id));

        CustomerEntity customer = customerRepository.findByPhoneNumber(deliveryRequestDTO.phoneNumber())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con teléfono: " + deliveryRequestDTO.phoneNumber()));

        TurnsEntity turn = turnsRepository.findById(deliveryRequestDTO.turnId())
                .orElseThrow(() -> new RuntimeException("Turno no encontrado con id: " + deliveryRequestDTO.turnId()));

        // Actualizar campos
        delivery.setCustomer(customer);
        delivery.setTurn(turn);
        delivery.setPaid(deliveryRequestDTO.paid());
        delivery.setCreatedAt(deliveryRequestDTO.createdAt());
        delivery.setAssignedSlot(deliveryRequestDTO.assignedSlot());
        delivery.setRescheduledSlot(deliveryRequestDTO.rescheduledSlot());
        delivery.setWasRescheduled(deliveryRequestDTO.wasRescheduled());
        delivery.setQueuePosition(deliveryRequestDTO.queuePosition());
        delivery.setCancelableUntil(deliveryRequestDTO.cancelableUntil());
        delivery.setThankYouMessage(deliveryRequestDTO.thankYouMessage());

        DeliveryEntity updated = deliveryRepository.save(delivery);
        return DeliveryMapper.toDTO(updated);
    }

    @Override
    public void deleteEntity(Long id) {
        DeliveryEntity delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada con id: " + id));
        deliveryRepository.delete(delivery);
    }

    @Override
    public List<DeliveryResponseDTO> getByPhoneNumber(String phoneNumber) {
        List<DeliveryEntity> deliveries = deliveryRepository.findByCustomer_PhoneNumber(phoneNumber);
        return deliveries.stream()
                .map(DeliveryMapper::toDTO)
                .toList();
    }

    @Override
    public List<DeliveryResponseDTO> searchByCustomerName(String name) {
        List<DeliveryEntity> deliveries = deliveryRepository.findByCustomer_FullNameContainingIgnoreCase(name);
        return deliveries.stream()
                .map(DeliveryMapper::toDTO)
                .toList();
    }
}
