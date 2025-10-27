package com.filazero.demo.detailDelivery;

import org.springframework.stereotype.Component;

import com.filazero.demo.detailDelivery.dtos.DetailDeliveryResponseDTO;

@Component
public class DetailDeliveryMapper {

    public DetailDeliveryResponseDTO toResponseDTO(DetailDeliveryEntity entity) {
        if (entity == null) {
            return null;
        }

        var product = entity.getProduct();

        return new DetailDeliveryResponseDTO(
            entity.getId(),
            entity.getDelivery().getId(),
            product.getId(),
            product.getName(),
            product.getDescription(),
            entity.getQuantity(),
            entity.getUnitPrice(),
            entity.getSubtotal()
        );
    }
}