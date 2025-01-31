package dev.magadiflo.app.command.rest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRestModel {
    @NotBlank(message = "Product title is a required field")
    private String title;

    @Min(value = 1, message = "Price must be greater than or equal to 1")
    private BigDecimal price;

    @Min(value = 0, message = "Quantity must be greater than or equal to zero")
    @Max(value = 5, message = "Quantity must be less than or equal to 5")
    private Integer quantity;
}
