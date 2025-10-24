package com.filazero.demo.customer;

import org.springframework.stereotype.Component;
import com.filazero.demo.customer.dtos.CustomerResponseDTO; 
import com.filazero.demo.customer.dtos.CustomerRequestDTO;
import com.filazero.demo.profile.ProfileMapper;
import com.filazero.demo.profile.dtos.ProfileResponseDTO;
import com.filazero.demo.role.RoleEntity;
import com.filazero.demo.role.dtos.RoleResponseDTO;


@Component
public class CustomerMapper {

    private final ProfileMapper profileMapper;

    public CustomerMapper(ProfileMapper profileMapper) {
        this.profileMapper = profileMapper;
    }

    // Convertir CustomerEntity → CustomerResponseDTO
    public CustomerResponseDTO toResponseDTO(CustomerEntity entity) {
        if (entity == null) return null;

        RoleResponseDTO roleDTO = null;
        if (entity.getRole() != null) {
            roleDTO = new RoleResponseDTO(entity.getRole().getId_role(), entity.getRole().getName());
        }

        ProfileResponseDTO profileDTO = profileMapper.toResponseDTO(entity.getProfile());

        return new CustomerResponseDTO(
            entity.getId(),
            entity.getUsername(),
            profileDTO,
            roleDTO //no set role
        );
    }

     public CustomerEntity toEntity(CustomerRequestDTO dto, RoleEntity role) {
        if (dto == null) return null;

        CustomerEntity entity = new CustomerEntity();
        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity.setRole(role);
        return entity;
    }

    public void updateEntityFromDTO(CustomerRequestDTO dto, CustomerEntity entity, RoleEntity role) {
        if (dto.username() != null) entity.setUsername(dto.username());
        if (dto.email() != null) entity.setEmail(dto.email());
        if (dto.password() != null) entity.setPassword(dto.password());
        if (role != null) entity.setRole(role);
    }

    public CustomerRequestDTO toRequestDTO(CustomerEntity entity) {
        if (entity == null) return null;

        Long roleId = (entity.getRole() != null) ? entity.getRole().getId_role() : null;

        return new CustomerRequestDTO(
            entity.getUsername(),
            entity.getEmail(),
            entity.getPassword(),
            roleId
        );
    }
}
