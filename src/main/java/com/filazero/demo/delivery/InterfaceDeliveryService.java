package com.filazero.demo.delivery;

import java.util.List;

import com.filazero.demo.delivery.dtos.DeliveryRequestDTO;
import com.filazero.demo.delivery.dtos.DeliveryResponseDTO;
import com.filazero.demo.implementations.IService;

public interface InterfaceDeliveryService extends IService<DeliveryResponseDTO, DeliveryRequestDTO> {

     // Buscar entrega por número de teléfono del cliente
    List<DeliveryResponseDTO> getByPhoneNumber(String phoneNumber);

    // Buscar entregas por nombre del cliente??
    List<DeliveryResponseDTO> searchByCustomerName(String name);
}

