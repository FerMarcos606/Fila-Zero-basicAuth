package com.filazero.demo.delivery.dtos;

import java.util.List;

import com.filazero.demo.detailDelivery.dtos.DetailDeliveryRequestDTO;

// DeliveryRequestDTO
public record DeliveryRequestDTO(
   
    String phoneNumber,
    Boolean paid,  
    List<DetailDeliveryRequestDTO> details 
) {}




    


