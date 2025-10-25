package com.filazero.demo.delivery;

import java.time.LocalDateTime;
import java.util.List;

import com.filazero.demo.customer.CustomerEntity;
import com.filazero.demo.detailDelivery.DetailDeliveryEntity;
import com.filazero.demo.enums.DeliveryStatus;
import com.filazero.demo.nofications.NotificationsEntity;
import com.filazero.demo.turns.TurnsEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "deliveries")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @OneToOne
    @JoinColumn(name = "turn_id", nullable = false)
    private TurnsEntity turn;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<DetailDeliveryEntity> details;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private Boolean paid;

    private LocalDateTime createdAt;

        // Turno asignado
    private LocalDateTime assignedSlot;

    // nuevo turno
    private LocalDateTime rescheduledSlot;

        //turno reprogramado
    private Boolean wasRescheduled;

    private Integer queuePosition;

    private LocalDateTime cancelableUntil;

   private String thankYouMessage;

    @OneToMany(mappedBy = "delivery")
    private List<NotificationsEntity> notifications;
}



