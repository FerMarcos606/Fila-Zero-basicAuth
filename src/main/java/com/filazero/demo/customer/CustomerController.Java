package com.filazero.demo.customer;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filazero.demo.customer.dtos.CustomerRequestDTO;
import com.filazero.demo.customer.dtos.CustomerResponseDTO;

import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("${api-endpoint}/customer")  

@RequiredArgsConstructor

public class CustomerController {

    private final CustomerServiceImpl customerService;

    // GET → todos los clientes
    @GetMapping
    public List<CustomerResponseDTO> getAll() {
        return customerService.getEntities();
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getById(@PathVariable Long id) {
        return customerService.getByID(id);
    }

    // POST → crear cliente
    @PostMapping
    public CustomerResponseDTO create(@RequestBody CustomerRequestDTO dto) {
        return customerService.createEntity(dto);
    }

//     // PUT → actualizar cliente
//     @PutMapping("/CustomerController cannot be resolved to a type en el CustomerControllerTest
// Entity(id, dto);
//     }

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

