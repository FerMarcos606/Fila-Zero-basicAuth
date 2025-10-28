package com.filazero.demo.customer.dtos;

import com.filazero.demo.profile.dtos.ProfileResponseDTO;
import com.filazero.demo.role.dtos.RoleResponseDTO;

public record CustomerResponseDTO(

Long id,
String username,
String email,  
ProfileResponseDTO profile,
RoleResponseDTO role // for many roles Set<RoleResponseDTO> roles

) {} 