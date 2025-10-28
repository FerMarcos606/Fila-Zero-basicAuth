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

    // posición actual en la cola
    @Column(nullable = false)
    private Integer position;

    // hora en que se ingresó a la cola
    @Column(nullable = false)
    private LocalDateTime joinedAt;

    // estado: activo, atendido, cancelado (opcional por ahora)
    @Column(nullable = false)
    private Boolean active = true;

    // 🔗 Relación con DeliveryEntity
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", nullable = false, unique = true)
    private DeliveryEntity delivery;
}
