package com.filazero.demo.customer;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    // Usaremos 'email' como identificador único???
         
    Optional<CustomerEntity> findByEmail(String email);
    Optional<CustomerEntity> findByProfile_PhoneNumber(String phoneNumber);
    List<CustomerEntity> findByProfile_NameContainingIgnoreCase(String name);


    }

        // // En CustomerRepository.java
        // @Query("SELECT c FROM CustomerEntity c JOIN FETCH c.profile WHERE c.id = :id")
        // Optional<CustomerEntity> findByIdWithProfile(Long id);