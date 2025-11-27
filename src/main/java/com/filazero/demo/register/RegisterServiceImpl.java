package com.filazero.demo.register;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

// Tus imports de entidades y repositorios
import com.filazero.demo.customer.CustomerEntity;
import com.filazero.demo.customer.CustomerRepository;
import com.filazero.demo.profile.ProfileEntity;
import com.filazero.demo.role.RoleEntity;
import com.filazero.demo.role.RoleRepository;
import com.filazero.demo.register.dtos.RegisterRequestDTO;
import com.filazero.demo.register.dtos.RegisterResponseDTO;;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO dto) {
        
        // Validar email duplicado
        if (customerRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("Email ya registrado");
        }

        // Validar DNI duplicado (opcional, si quieres)
        // if (profileRepository.findByDni(dto.dni()).isPresent()) {
        //     throw new IllegalArgumentException("DNI ya registrado");
        // }

        // Obtener rol CUSTOMER por defecto
        RoleEntity customerRole = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role CUSTOMER not found"));

        // Crear Customer
        CustomerEntity customer = new CustomerEntity();
        customer.setEmail(dto.email());
        customer.setUsername(dto.username());
        customer.setPassword(passwordEncoder.encode(dto.password())); // 🔐 Hashear
        customer.setRole(customerRole);

        // Crear Profile
        ProfileEntity profile = new ProfileEntity();
        profile.setDni(dto.dni());
        profile.setName(dto.name());
        profile.setFirstSurname(dto.firstSurname());
        profile.setSecondSurname(dto.secondSurname());
        profile.setPhoneNumber(dto.phoneNumber());
        profile.setAvatar(dto.avatar());
        profile.setCustomer(customer);

        customer.setProfile(profile);

        // Guardar (cascade guardará también el profile)
        CustomerEntity saved = customerRepository.save(customer);

        // Retornar DTO
        return new RegisterResponseDTO(
            saved.getId(),
            saved.getEmail(),
            saved.getUsername(),
            saved.getProfile().getDni(),
            saved.getProfile().getName(),
            saved.getProfile().getFirstSurname(),
            saved.getProfile().getSecondSurname(),
            saved.getProfile().getPhoneNumber(),
            saved.getRole().getName()
        );
    }
}

