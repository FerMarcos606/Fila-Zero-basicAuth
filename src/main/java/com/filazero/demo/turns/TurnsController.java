package com.filazero.demo.turns;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.filazero.demo.turns.dtos.TurnsResponseDTO;

@RestController
@RequestMapping("${api-endpoint}/turns")
public class TurnsController {

    private final TurnsServiceImpl turnsService;

    public TurnsController(TurnsServiceImpl turnsService) {
        this.turnsService = turnsService;
    }

    // 🔹 Obtener todos los turnos (por ejemplo, para mostrar horarios disponibles)
    @GetMapping
    public ResponseEntity<List<TurnsResponseDTO>> getAllTurns() {
        List<TurnsResponseDTO> turns = turnsService.getEntities();
        return ResponseEntity.ok(turns);
    }

    // 🔹 Obtener un turno por su ID
    @GetMapping("/{id}")
    public ResponseEntity<TurnsResponseDTO> getTurnById(@PathVariable Long id) {
        TurnsResponseDTO turn = turnsService.getByID(id);
        return ResponseEntity.ok(turn);
    }
}





























































