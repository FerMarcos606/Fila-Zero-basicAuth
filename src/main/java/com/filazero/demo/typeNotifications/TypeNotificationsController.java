package com.filazero.demo.typeNotifications;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filazero.demo.typeNotifications.dtos.TypeNotificationsResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api-endpoint}/typeNotifications")
@RequiredArgsConstructor
public class TypeNotificationsController {

    private final TypeNotificationsRepository repository;
    private final TypeNotificationsMapper mapper;

    // GET /api/type-notifications - Listar todos
    @GetMapping
    public ResponseEntity<List<TypeNotificationsResponseDTO>> getAll() {
        List<TypeNotificationsResponseDTO> types = repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(types);
    }

    // GET /api/type-notifications/{id} - Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<TypeNotificationsResponseDTO> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/type-notifications/name/{name} - Buscar por nombre (útil para el sistema)
    @GetMapping("/name/{name}")
    public ResponseEntity<TypeNotificationsResponseDTO> getByName(@PathVariable String name) {
        return repository.findByName(name)
                .map(mapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}