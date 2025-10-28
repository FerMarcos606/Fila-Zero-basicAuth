package com.filazero.demo.delivery;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.filazero.demo.delivery.dtos.DeliveryRequestDTO;
import com.filazero.demo.delivery.dtos.DeliveryResponseDTO;

@RestController
@RequestMapping("${api-endpoint}/deliveries")
public class DeliveryController {

    private final InterfaceDeliveryService deliveryService;

    public DeliveryController(InterfaceDeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    // Obtener todas las entregas
    @GetMapping
    public ResponseEntity<List<DeliveryResponseDTO>> getAll() {
        return ResponseEntity.ok(deliveryService.getEntities());
    }

    // Obtener entrega por ID
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.getByID(id));
    }

    // Crear una entrega
    @PostMapping
    public ResponseEntity<DeliveryResponseDTO> create(@RequestBody DeliveryRequestDTO deliveryRequestDTO) {
        DeliveryResponseDTO response = deliveryService.createEntity(deliveryRequestDTO);
        return ResponseEntity.ok(response);
    }

    // Actualizar una entrega
    @PutMapping("/{id}")
    public ResponseEntity<DeliveryResponseDTO> update(@PathVariable Long id, 
                                                      @RequestBody DeliveryRequestDTO deliveryRequestDTO) {
        DeliveryResponseDTO updated = deliveryService.updateEntity(id, deliveryRequestDTO);
        return ResponseEntity.ok(updated);
    }

    // Eliminar una entrega
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deliveryService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar entregas por número de teléfono del cliente
    @GetMapping("/search/phone")
    public ResponseEntity<List<DeliveryResponseDTO>> getByPhoneNumber(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(deliveryService.getByPhoneNumber(phoneNumber));
    }

      // Buscar entregas por nombre del cliente
    @GetMapping("/search/name")
    public ResponseEntity<List<DeliveryResponseDTO>> searchByCustomerName(@RequestParam String name) {
        return ResponseEntity.ok(deliveryService.searchByCustomerName(name));
    }
}