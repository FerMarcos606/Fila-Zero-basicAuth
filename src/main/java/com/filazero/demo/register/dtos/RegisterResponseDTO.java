package com.filazero.demo.register.dtos;

public record RegisterResponseDTO(
    Long id,
    String email,
    String username,
    String dni,
    String name,
    String firstSurname,
    String secondSurname,
    String phoneNumber,
    String roleName
) {}
