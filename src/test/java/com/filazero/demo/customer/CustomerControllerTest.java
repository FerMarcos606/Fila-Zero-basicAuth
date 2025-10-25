package com.filazero.demo.customer;

import com.filazero.demo.customer.dtos.CustomerRequestDTO;
import com.filazero.demo.customer.dtos.CustomerResponseDTO;
import com.filazero.demo.role.dtos.RoleResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Long id = 1L;
        CustomerResponseDTO mockResponse = new CustomerResponseDTO(
                id,
                "Fer",
                "fer@example.com",
                null,
                new RoleResponseDTO(2L, "CUSTOMER")
        );

        when(customerService.getByID(id)).thenReturn(mockResponse);

        CustomerResponseDTO result = customerController.getById(id);

        assertNotNull(result);
        assertEquals("Fer", result.username());
        assertEquals("fer@example.com", result.email());
        verify(customerService).getByID(id);
    }

    @Test
    void testCreateCustomer() {
        CustomerRequestDTO request = new CustomerRequestDTO("Fer", "fer@example.com", "password123", 2L);
        CustomerResponseDTO mockResponse = new CustomerResponseDTO(
                1L,
                "Fer",
                "fer@example.com",
                null,
                new RoleResponseDTO(2L, "CUSTOMER")
        );

        when(customerService.createEntity(request)).thenReturn(mockResponse);

        CustomerResponseDTO result = customerController.create(request);

        assertNotNull(result);
        assertEquals("Fer", result.username());
        assertEquals("fer@example.com", result.email());
        verify(customerService).createEntity(request);
    }

    @Test
    void testDeleteCustomer() {
        Long id = 1L;
        doNothing().when(customerService).deleteEntity(id);

        customerController.delete(id);

        verify(customerService).deleteEntity(id);
    }

    @Test
    void testGetByEmail() {
        String email = "fer@example.com";
        CustomerResponseDTO mockResponse = new CustomerResponseDTO(
                1L,
                "Fer",
                email,
                null,
                new RoleResponseDTO(2L, "CUSTOMER")
        );

        when(customerService.getByEmail(email)).thenReturn(mockResponse);

        CustomerResponseDTO result = customerController.getByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.email());
        verify(customerService).getByEmail(email);
    }

    @Test
    void testGetAllCustomers() {
        CustomerResponseDTO customer1 = new CustomerResponseDTO(1L, "Fer", "fer@example.com", null, null);
        CustomerResponseDTO customer2 = new CustomerResponseDTO(2L, "Ana", "ana@example.com", null, null);

        when(customerService.getEntities()).thenReturn(List.of(customer1, customer2));

        List<CustomerResponseDTO> result = customerController.getAll();

        assertEquals(2, result.size());
        assertEquals("Fer", result.get(0).username());
        assertEquals("Ana", result.get(1).username());
        verify(customerService).getEntities();
    }
}
