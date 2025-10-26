package com.filazero.demo.turns;

import com.filazero.demo.delivery.DeliveryEntity;
import com.filazero.demo.enums.TurnsStatus;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "turns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   //Code for customer
    @Column(unique = true, nullable = false)
    private String code;
    
    @Column(nullable = false)
    private LocalDateTime startTime;

   
    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private LocalDateTime timeSlot;

  
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TurnsStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", unique = true)
    private DeliveryEntity delivery;

   
}
