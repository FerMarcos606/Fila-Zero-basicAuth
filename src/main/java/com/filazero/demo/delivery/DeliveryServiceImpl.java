package com.filazero.demo.delivery;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.filazero.demo.customer.CustomerEntity;
import com.filazero.demo.customer.CustomerRepository;
import com.filazero.demo.delivery.dtos.DeliveryRequestDTO;
import com.filazero.demo.delivery.dtos.DeliveryResponseDTO;
import com.filazero.demo.detailDelivery.DetailDeliveryEntity;
import com.filazero.demo.detailDelivery.dtos.DeliveryDetailRequestDTO;
import com.filazero.demo.enums.DeliveryStatus;
import com.filazero.demo.products.ProductEntity;
import com.filazero.demo.turns.TurnsEntity;
import com.filazero.demo.turns.TurnsRepository;
import com.filazero.demo.products.ProductRepository;


@Service("deliveryService")
@Transactional
public class DeliveryServiceImpl implements InterfaceDeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final CustomerRepository customerRepository;
    private final TurnsRepository turnsRepository;
    private final ProductRepository productRepository; 

   public DeliveryServiceImpl(
        DeliveryRepository deliveryRepository,
        CustomerRepository customerRepository,
        TurnsRepository turnsRepository,
        ProductRepository productRepository) { 
    this.deliveryRepository = deliveryRepository;
    this.customerRepository = customerRepository;
    this.turnsRepository = turnsRepository;
    this.productRepository = productRepository; 
}
    @Override
    public List<DeliveryResponseDTO> getEntities() {
        return deliveryRepository.findAll().stream()
                .map(DeliveryMapper::toDTO)
                .toList();
    }

    @Override
public DeliveryResponseDTO createEntity(DeliveryRequestDTO deliveryRequestDTO) {

    DeliveryEntity delivery = new DeliveryEntity();

    CustomerEntity customer = customerRepository.findByProfile_PhoneNumber(deliveryRequestDTO.phoneNumber())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con teléfono: " + deliveryRequestDTO.phoneNumber()));

    TurnsEntity turn = turnsRepository.findById(deliveryRequestDTO.turnId())
            .orElseThrow(() -> new RuntimeException("Turno no encontrado con id: " + deliveryRequestDTO.turnId()));

    delivery.setCustomer(customer);
    delivery.setTurn(turn);
    delivery.setPaid(deliveryRequestDTO.paid());
    delivery.setCreatedAt(LocalDateTime.now());
    delivery.setStatus(DeliveryStatus.EN_PREPARACION);
    delivery.setThankYouMessage("Gracias por su compra");
    delivery.setAssignedSlot(deliveryRequestDTO.assignedSlot());

    BigDecimal total = BigDecimal.ZERO;
    List<DetailDeliveryEntity> details = new ArrayList<>();

    for (DeliveryDetailRequestDTO item : deliveryRequestDTO.details()) {
        ProductEntity product = productRepository.findById(item.productId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + item.productId()));

        DetailDeliveryEntity detail = new DetailDeliveryEntity();
        detail.setProduct(product);
        detail.setQuantity(item.quantity());
        detail.setUnitPrice(product.getPrice());
        detail.setDelivery(delivery);

        BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(item.quantity()));
        detail.setSubtotal(subtotal);

        total = total.add(subtotal);
        details.add(detail);
    }

    delivery.setDetails(details);
    delivery.setTotal(total);

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

        CustomerEntity customer = customerRepository.findByProfile_PhoneNumber(deliveryRequestDTO.phoneNumber())
    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con teléfono: " + deliveryRequestDTO.phoneNumber()));


        TurnsEntity turn = turnsRepository.findById(deliveryRequestDTO.turnId())
                .orElseThrow(() -> new RuntimeException("Turno no encontrado con id: " + deliveryRequestDTO.turnId()));

        // Actualizar campos
        delivery.setCustomer(customer);
        delivery.setTurn(turn);
        delivery.setPaid(deliveryRequestDTO.paid());
        delivery.setCreatedAt(LocalDateTime.now());
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
        List<DeliveryEntity> deliveries = deliveryRepository.findByCustomer_Profile_PhoneNumber(phoneNumber);
        return deliveries.stream()
                .map(DeliveryMapper::toDTO)
                .toList();
    }

    @Override
    public List<DeliveryResponseDTO> searchByCustomerName(String name) {
        List<DeliveryEntity> deliveries = deliveryRepository.findByCustomer_Profile_NameContainingIgnoreCase(name);
        return deliveries.stream()
                .map(DeliveryMapper::toDTO)
                .toList();
    
    }
  } 
