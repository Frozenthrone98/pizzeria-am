package com.pizzeria.am.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
    private Long idPizza;
    private Double newPrice;
}
