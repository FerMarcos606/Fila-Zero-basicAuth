package com.filazero.demo.typeNotifications;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "type_notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
public class TypeNotificationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long id;

    // Nombre único del tipo (ej: "DELIVERY_ASSIGNED", "DELIVERY_IN_PROGRESS", "TURN_ASSIGNED")
    @Column(nullable = false, unique = true, length = 50)
    @ToString.Include
    private String name;

    // Descripción legible (ej: "Tu pedido ha sido asignado a un repartidor")
    @Column(nullable = false, length = 255)
    private String description;

    // Status asociado al tipo de notificación (ej: "En preparación", "Turno asignado")
    @Column(nullable = false, length = 100)
    private String status;
}
