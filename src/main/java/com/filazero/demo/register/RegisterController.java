package com.filazero.demo.register;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filazero.demo.register.dtos.RegisterRequestDTO;
import com.filazero.demo.register.dtos.RegisterResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api-endpoint}/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO dto) {
        RegisterResponseDTO response = registerService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

