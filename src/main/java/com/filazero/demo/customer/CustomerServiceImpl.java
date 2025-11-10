package com.filazero.demo.customer;

import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import com.filazero.demo.customer.dtos.CustomerRequestDTO;
import com.filazero.demo.customer.dtos.CustomerResponseDTO;
import com.filazero.demo.role.RoleEntity;
import com.filazero.demo.role.RoleRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final RoleRepository roleRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, RoleRepository roleRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<CustomerResponseDTO> getEntities() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponseDTO)
                .toList();
    }

    @Override
    public CustomerResponseDTO createEntity(CustomerRequestDTO dto) {
        // Validar duplicados
        if (customerRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("Email ya registrado");
        }
            // 🔹 Si querés validar el nombre del perfil en vez del username
        if (!customerRepository.findByProfile_NameContainingIgnoreCase(dto.username()).isEmpty()) {
        throw new IllegalArgumentException("Nombre de usuario ya registrado");
    }


        RoleEntity role = roleRepository.findById(dto.roleId())
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));

        CustomerEntity entity = customerMapper.toEntity(dto, role);
        CustomerEntity saved = customerRepository.save(entity);
        return customerMapper.toResponseDTO(saved);
    }

    @Override
    public CustomerResponseDTO getByID(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    @Override
    public CustomerResponseDTO updateEntity(Long id, CustomerRequestDTO dto) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        RoleEntity role = roleRepository.findById(dto.roleId())
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));

        customerMapper.updateEntityFromDTO(dto, entity, role);
        CustomerEntity updated = customerRepository.save(entity);
        return customerMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteEntity(Long id) {
        customerRepository.deleteById(id);
    }

    // ✅ Buscar por nombre (ya que username no existe en Profile)
    @Override
    public List<CustomerResponseDTO> searchByName(String name) {
        return customerRepository.findByProfile_NameContainingIgnoreCase(name)
                .stream()
                .map(customerMapper::toResponseDTO)
                .toList();
    }

    @Override
    public CustomerResponseDTO getByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(customerMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    // Opcional: buscar por phoneNumber desde Profile
    public CustomerResponseDTO getByPhoneNumber(String phoneNumber) {
        return customerRepository.findByProfile_PhoneNumber(phoneNumber)
                .map(customerMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }
}
