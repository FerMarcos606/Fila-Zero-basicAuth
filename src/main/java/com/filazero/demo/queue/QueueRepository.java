package com.filazero.demo.queue;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<QueueEntity, Long> {

    QueueEntity findByDelivery_Id(Long deliveryId);
}

