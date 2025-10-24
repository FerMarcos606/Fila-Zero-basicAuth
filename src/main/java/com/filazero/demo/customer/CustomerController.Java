package com.filazero.demo.customer;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

package com.filazero.demo.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;

    // GET → todos los clientes
    @GetMapping
    public List<CustomerResponseDTO> getAll() {
        return customerService.getEntities();
    }

    // GET → cliente por id
    @GetMapping("/{id}")
    public CustomerResponseDTO getById(@PathVariable Long id) {
        return customerService.getByID(id);
    }

    // POST → crear cliente
    @PostMapping
    public CustomerResponseDTO create(@RequestBody CustomerRequestDTO dto) {
        return customerService.createEntity(dto);
    }

    // PUT → actualizar cliente
    @PutMapping("/{id}")
    public CustomerResponseDTO update(@PathVariable Long id, @RequestBody CustomerRequestDTO dto) {
        return customerService.updateEntity(id, dto);
    }

    // DELETE → eliminar cliente
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerService.deleteEntity(id);
    }

    // GET → buscar cliente por username (future offers)
    @GetMapping("/search")
    public List<CustomerResponseDTO> searchByName(@RequestParam String name) {
        return customerService.searchByName(name);
    }

    // GET → buscar cliente por email
    @GetMapping("/email")
    public CustomerResponseDTO getByEmail(@RequestParam String email) {
        return customerService.getByEmail(email);
    }
}

