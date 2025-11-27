package com.filazero.demo.register;


import com.filazero.demo.register.dtos.RegisterRequestDTO;
import com.filazero.demo.register.dtos.RegisterResponseDTO;

public interface RegisterService {
    RegisterResponseDTO register(RegisterRequestDTO dto);
}
