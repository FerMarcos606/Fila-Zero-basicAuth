package com.filazero.demo.profile;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filazero.demo.profile.dtos.ProfileRequestDTO;
import com.filazero.demo.profile.dtos.ProfileResponseDTO;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor  // ← Genera el constructor automáticamente
public class ProfileServiceImpl implements IProfileService {  // ← Ahora implementa IProfileService

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ProfileResponseDTO createEntity(ProfileRequestDTO dto) {
        ProfileEntity entity = profileMapper.toEntity(dto);
        ProfileEntity saved = profileRepository.save(entity);
        return profileMapper.toResponseDTO(saved);  // ← Cambiar a toResponseDTO
    }

    @Override
    public ProfileResponseDTO getByID(Long id) {
        ProfileEntity entity = profileRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Perfil no encontrado con ID: " + id));
        return profileMapper.toResponseDTO(entity);  // ← Cambiar a toResponseDTO
    }

    @Override
    public ProfileResponseDTO updateEntity(Long id, ProfileRequestDTO dto) {
        ProfileEntity entity = profileRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Perfil no encontrado con ID: " + id));
        profileMapper.updateEntity(entity, dto);  // ← Método para actualizar
        ProfileEntity updated = profileRepository.save(entity);
        return profileMapper.toResponseDTO(updated);  // ← Cambiar a toResponseDTO
    }

    @Override
    public void deleteEntity(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new RuntimeException("Perfil no encontrado con ID: " + id);
        }
        profileRepository.deleteById(id);
    }

    @Override
    public List<ProfileResponseDTO> getEntities() {
        return profileRepository.findAll().stream()
            .map(profileMapper::toResponseDTO)  // ← Cambiar a toResponseDTO
            .collect(Collectors.toList());
    }
}