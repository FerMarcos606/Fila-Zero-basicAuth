package com.filazero.demo.products;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

import com.filazero.demo.detailDelivery.DetailDeliveryEntity;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = true)
    private Boolean available;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<DetailDeliveryEntity> details;
}
