package com.filazero.demo.delivery;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.filazero.demo.customer.CustomerEntity;
import com.filazero.demo.detailDelivery.DetailDeliveryEntity;
import com.filazero.demo.notifications.NotificationsEntity;
import com.filazero.demo.queue.QueueEntity;
import com.filazero.demo.turns.TurnsEntity;
import com.filazero.demo.queue.QueueEntity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean paid;

    private LocalDateTime createdAt;

    private LocalDateTime assignedSlot;

    private LocalDateTime rescheduledSlot;

    private Boolean wasRescheduled;

    private Integer queuePosition;

    private LocalDateTime cancelableUntil;

    private String thankYouMessage;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column(nullable = false)
    private BigDecimal total;

    // Relaciones

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Column(nullable = false)
    private BigDecimal total; // 💰 nuevo campo total del pedido

    // Relaciones
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "turn_id")
    private TurnsEntity turn;

    // Relación con QueueEntity: Delivery "posee" la Queue (mappedBy en Queue)
    @OneToOne(mappedBy = "delivery", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private QueueEntity queue;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // Relación con QueueEntity
    @OneToOne(mappedBy = "delivery", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private QueueEntity queue;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NotificationsEntity> notifications;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DetailDeliveryEntity> details;
    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DetailDeliveryEntity> details;
}



