package com.filazero.demo.notifications;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.filazero.demo.customer.CustomerEntity;
import com.filazero.demo.delivery.DeliveryEntity;
import com.filazero.demo.typeNotifications.TypeNotificationsEntity;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"customer", "delivery"}) // Evitar lazy loading en toString
public class NotificationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // customer_id: FK al cliente receptor (CustomerEntity)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    // delivery_id: FK al pedido/entrega (DeliveryEntity). Opcional.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private DeliveryEntity delivery;

    // Fecha y hora
    @Builder.Default
    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime = LocalDateTime.now();

    // type_notification_id: FK al tipo de notificación (TypeNotificationEntity)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_notification_id", nullable = false)
    private TypeNotificationsEntity typeNotification;

    // Indica si ha sido leída
    @Builder.Default
    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;
}