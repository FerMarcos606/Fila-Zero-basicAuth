package com.filazero.demo.customer;

import com.filazero.demo.customer.dtos.CustomerRequestDTO;
import com.filazero.demo.customer.dtos.CustomerResponseDTO;
import com.filazero.demo.profile.ProfileEntity;
import com.filazero.demo.profile.dtos.ProfileRequestDTO;
import com.filazero.demo.profile.dtos.ProfileResponseDTO;
import com.filazero.demo.role.RoleEntity;
import com.filazero.demo.role.RoleRepository;
import com.filazero.demo.role.dtos.RoleResponseDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer() {
        // DTO de entrada
        CustomerRequestDTO dto = new CustomerRequestDTO(
                "fer123", "fer@mail.com", "password123", 1L
        );

       // Role simulado
        RoleEntity role = new RoleEntity();
        role.setId_role(1L);
        role.setName("CUSTOMER");

        // DTO de rol para la respuesta
        RoleResponseDTO roleDTO = new RoleResponseDTO(1L, "CUSTOMER");

        // Entidad simulada
        CustomerEntity entity = new CustomerEntity();
        entity.setId(1L);
        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity.setRole(role);

        // DTO de respuesta esperado
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(
            1L,
            "fer123",
            "fer@mail.com",
            null,
            roleDTO
        );

        // Configuración de mocks
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(customerMapper.toEntity(dto, role)).thenReturn(entity);
        when(customerRepository.save(entity)).thenReturn(entity);
        when(customerMapper.toResponseDTO(entity)).thenReturn(responseDTO);

        // Ejecución
        CustomerResponseDTO result = customerService.createEntity(dto);

        // Verificaciones
        assertEquals("fer123", result.username());
        assertEquals("CUSTOMER", result.role().name());

        verify(roleRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(entity);
        verify(customerMapper, times(1)).toEntity(dto, role);
        verify(customerMapper, times(1)).toResponseDTO(entity);
    }

        @Test
        void testRoleDTO() {
        RoleResponseDTO roleDTO = new RoleResponseDTO(1L, "CUSTOMER");
        assertEquals("CUSTOMER", roleDTO.name());
    }

    @Test
    void shouldCreateCustomerWithProfileAndRole() {
        // DTO de entrada
        CustomerRequestDTO dto = new CustomerRequestDTO(
        "fer123",  // username
        "fer.hola@gmail.com", // email
        "123/B*55",  1L);

        new ProfileRequestDTO(
        "Fernanda",       // name
        "García",         // firstSurname
        "Rojas",          // secondSurname
        "12345678L",      // dni
        "123456789",      // phoneNumber
        "avatar.png"      // avatar
        );


        // Entidades simuladas
        RoleEntity role = new RoleEntity();
        role.setId_role(1L);
        role.setName("ROLE_USER");

        ProfileEntity profile = new ProfileEntity();
        profile.setName("Fer");
        profile.setFirstSurname("García");
        profile.setPhoneNumber("123456789");
        // profile.setAvatar("avatar.png");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setUsername("fer123");
        customerEntity.setPassword("securepass");
        customerEntity.setRole(role);
        customerEntity.setProfile(profile);

        // Primero creás el perfil
        ProfileResponseDTO profileDTO = new ProfileResponseDTO(
            1L,
            "Fernanda",     // name
            "García",       // firstSurname
            "Rojas",        // secondSurname
            "12345678L",    // dni
            "123456789",    // phoneNumber
            "avatar.png"   
        );

        // Luego el rol
        RoleResponseDTO roleDTO = new RoleResponseDTO(
            1L,
            "ROLE_USER"
        );

        // Y ahora sí, el CustomerResponseDTO completo
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(
            1L,
            "fer123",
            "fer.hola@gmail.com",
            profileDTO,
            roleDTO
        );

    // Mocks
    when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
    when(customerMapper.toEntity(dto, role)).thenReturn(customerEntity);
    when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
    when(customerMapper.toResponseDTO(customerEntity)).thenReturn(responseDTO);

    // Llamada al método genérico
    CustomerResponseDTO result = customerService.createEntity(dto);

    // Verificaciones
    assertEquals("fer123", result.username());
    assertEquals("ROLE_USER", result.role().name());
    assertEquals("Fernanda", result.profile().name());
}



}
