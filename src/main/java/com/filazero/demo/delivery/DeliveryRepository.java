package com.filazero.demo.delivery;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
    
    // search delivery buy Profile phone number
    List<DeliveryEntity> findByCustomer_Profile_PhoneNumber(String phoneNumber);
    List<DeliveryEntity> findByCustomer_Profile_NameContainingIgnoreCase(String name);

}
