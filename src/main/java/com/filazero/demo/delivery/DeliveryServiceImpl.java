package com.filazero.demo.delivery;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.filazero.demo.customer.CustomerEntity;
import com.filazero.demo.customer.CustomerRepository;
import com.filazero.demo.delivery.dtos.DeliveryRequestDTO;
import com.filazero.demo.delivery.dtos.DeliveryResponseDTO;
import com.filazero.demo.detailDelivery.DetailDeliveryEntity;
import com.filazero.demo.enums.DeliveryStatus;
import com.filazero.demo.products.ProductRepository;
import com.filazero.demo.turns.ITurnsService;
import com.filazero.demo.turns.TurnsEntity;

@Service("deliveryService")
public class DeliveryServiceImpl implements InterfaceDeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ITurnsService turnService;

    public DeliveryServiceImpl(
            DeliveryRepository deliveryRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            ITurnsService turnService) {
            this.deliveryRepository = deliveryRepository;
            this.customerRepository = customerRepository;
            this.productRepository = productRepository;
            this.turnService = turnService;
    }

    @Override
    public List<DeliveryResponseDTO> getEntities() {
        return deliveryRepository.findAll().stream()
                .map(DeliveryMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public DeliveryResponseDTO createEntity(DeliveryRequestDTO deliveryRequestDTO) {
        // Buscar customer
       CustomerEntity customer = customerRepository
        .findByProfile_PhoneNumber(deliveryRequestDTO.phoneNumber())
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado con teléfono: " + deliveryRequestDTO.phoneNumber()));


        // Crear delivery con estado PENDING por defecto
        DeliveryEntity delivery = new DeliveryEntity();
        delivery.setCustomer(customer);
        delivery.setPaid(deliveryRequestDTO.paid() != null ? deliveryRequestDTO.paid() : false);
        delivery.setStatus(DeliveryStatus.PENDING); // PENDING debe estar definido en tu enum DeliveryStatus

        // Asignar detalles de delivery si hay productos
        if (deliveryRequestDTO.details() != null && !deliveryRequestDTO.details().isEmpty()) {
            List<DetailDeliveryEntity> details = deliveryRequestDTO.details().stream().map(item -> {
                var product = productRepository.findById(item.productId())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + item.productId()));
                var detail = new DetailDeliveryEntity();
                detail.setProduct(product);
                detail.setQuantity(item.quantity());
                detail.setDelivery(delivery);
                return detail;
            }).toList();
            delivery.setDetails(details);
        }

        // Asignar turno (stub por ahora, se completará con automatizaciones)
        // TurnsEntity turn = turnService.asignarTurno(); // 
        // delivery.setTurn(turn);

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
        // Actualización mínima para Postman, detalles se pueden agregar luego
        delivery.setPaid(deliveryRequestDTO.paid());
        return DeliveryMapper.toDTO(deliveryRepository.save(delivery));
    }

    @Override
    public void deleteEntity(Long id) {
        DeliveryEntity delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada con id: " + id));
        deliveryRepository.delete(delivery);
    }

    @Override
    public List<DeliveryResponseDTO> getByPhoneNumber(String phoneNumber) {
        // Por ahora devuelve vacío hasta que tengamos relación phoneNumber → CustomerEntity
        return List.of();
    }

    @Override
    public List<DeliveryResponseDTO> searchByCustomerName(String name) {
    List<DeliveryEntity> deliveries = deliveryRepository.findByCustomer_Profile_NameContainingIgnoreCase(name);
    return deliveries.stream()
                     .map(DeliveryMapper::toDTO)
                     .toList();
}

}
