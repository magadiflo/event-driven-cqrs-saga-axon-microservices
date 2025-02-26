package dev.magadiflo.app.command.rest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateOrderRestModel {
    @NotBlank(message = "ProductId is a required field")
    private String productId;

    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    @Max(value = 5, message = "Quantity must be less than or equal to 5")
    private Integer quantity;

    @NotBlank(message = "AddressId is a required field")
    private String addressId;
}
