package com.filazero.demo.delivery;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long>{
    List<DeliveryEntity> findByName(String name);
    Optional<DeliveryEntity> findByBizumNumber(String identificationNumber);

}
