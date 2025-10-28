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
import com.filazero.demo.detailDelivery.dtos.DetailDeliveryRequestDTO;
import com.filazero.demo.products.ProductEntity;
import com.filazero.demo.products.ProductRepository;
import com.filazero.demo.turns.TurnsRepository;



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
    public DeliveryResponseDTO getByID(Long id) {
        return deliveryRepository.findById(id)
                .map(DeliveryMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada con id: " + id));
    }

    @Override
    public DeliveryResponseDTO createEntity(DeliveryRequestDTO deliveryRequestDTO) {

        DeliveryEntity delivery = new DeliveryEntity();
        delivery.setCustomer(
                customerRepository.findByProfile_PhoneNumber(deliveryRequestDTO.phoneNumber())
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"))
        );
        delivery.setPaid(deliveryRequestDTO.paid());
        delivery.setCreatedAt(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;
        List<DetailDeliveryEntity> details = new ArrayList<>();

        for (DetailDeliveryRequestDTO item : deliveryRequestDTO.details()) {
            ProductEntity product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + item.productId()));

            DetailDeliveryEntity detail = new DetailDeliveryEntity();
            detail.setProduct(product);
            detail.setQuantity(item.quantity());
            detail.setUnitPrice(product.getPrice());
            detail.setDelivery(delivery);

            // subtotal calculado automáticamente con @PrePersist/@PreUpdate
            details.add(detail);
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(item.quantity())));
        }

        delivery.setDetails(details);
        delivery.setTotal(total);

        DeliveryEntity saved = deliveryRepository.save(delivery);
        return DeliveryMapper.toDTO(saved);
    }

    @Override
    public DeliveryResponseDTO updateEntity(Long id, DeliveryRequestDTO deliveryRequestDTO) {
        DeliveryEntity delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada con id: " + id));

        CustomerEntity customer = customerRepository.findByProfile_PhoneNumber(deliveryRequestDTO.phoneNumber())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        delivery.setCustomer(customer);
        delivery.setPaid(deliveryRequestDTO.paid());
        delivery.setCreatedAt(LocalDateTime.now());

        // Actualizamos detalles: eliminamos los antiguos y agregamos los nuevos
        List<DetailDeliveryEntity> newDetails = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (DetailDeliveryRequestDTO item : deliveryRequestDTO.details()) {
            ProductEntity product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + item.productId()));

            DetailDeliveryEntity detail = new DetailDeliveryEntity();
            detail.setProduct(product);
            detail.setQuantity(item.quantity());
            detail.setUnitPrice(product.getPrice());
            detail.setDelivery(delivery);

            newDetails.add(detail);
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(item.quantity())));
        }

        delivery.getDetails().clear();
        delivery.getDetails().addAll(newDetails);
        delivery.setTotal(total);

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
        return deliveries.stream().map(DeliveryMapper::toDTO).toList();
    }

    @Override
    public List<DeliveryResponseDTO> searchByCustomerName(String name) {
        List<DeliveryEntity> deliveries = deliveryRepository.findByCustomer_Profile_NameContainingIgnoreCase(name);
        return deliveries.stream().map(DeliveryMapper::toDTO).toList();
    }
}
