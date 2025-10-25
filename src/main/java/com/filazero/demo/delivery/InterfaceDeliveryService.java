package com.filazero.demo.delivery;

import java.util.List;

import com.filazero.demo.delivery.dtos.DeliveryRequestDTO;
import com.filazero.demo.delivery.dtos.DeliveryResponseDTO;
import com.filazero.demo.implementations.IService;

public interface InterfaceDeliveryService extends IService<DeliveryResponseDTO, DeliveryRequestDTO> {

    List<DeliveryResponseDTO> getEntities();

    DeliveryResponseDTO createEntity(DeliveryRequestDTO deliveryRequestDTO);

    DeliveryResponseDTO getByID(Long id);

    DeliveryResponseDTO updateEntity(Long id, DeliveryRequestDTO deliveryRequestDTO);

    void deleteEntity(Long id);

    // Buscar entrega por número de teléfono del cliente
    List<DeliveryResponseDTO> getByPhoneNumber(String phoneNumber);

    // Buscar entregas por nombre del cliente (si lo tenés en CustomerEntity)
    List<DeliveryResponseDTO> searchByCustomerName(String name);
}

