package com.filazero.demo.detailDelivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailDeliveryRepository extends JpaRepository<DetailDeliveryEntity, Long> {
    
    List<DetailDeliveryEntity> findByDeliveryId(Long deliveryId);
}