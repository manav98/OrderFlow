package com.orderflow.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    @NotBlank
    private String sku;
    @NotBlank
    private String name;
    @Positive
    @NotNull
    private BigDecimal price;
}
