package com.filazero.demo.queue;

import java.time.LocalDateTime;

import com.filazero.demo.delivery.DeliveryEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "queue")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // queue position
    @Column(nullable = false)
    private Integer position;

    // estimated hour delivery retired
    private java.time.LocalDateTime estimatedTime;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", nullable = false, unique = true)
    private DeliveryEntity delivery;
}
