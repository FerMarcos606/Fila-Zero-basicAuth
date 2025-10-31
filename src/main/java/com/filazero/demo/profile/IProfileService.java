package com.filazero.demo.profile;


import com.filazero.demo.implementations.IService;
import com.filazero.demo.profile.dtos.ProfileRequestDTO;
import com.filazero.demo.profile.dtos.ProfileResponseDTO;

public interface IProfileService extends IService<ProfileResponseDTO, ProfileRequestDTO> {
    // aquí podés agregar métodos extra si querés
}

