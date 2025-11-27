package com.filazero.demo.register.dtos;

public record RegisterRequestDTO(
    // Datos de Customer
    String email,
    String username,
    String password,
    
    // Datos de Profile
    String dni,
    String name,
    String firstSurname,
    String secondSurname,
    String phoneNumber,
    String avatar  // opcional
) {}
