package dev.magadiflo.app.command.rest;

import dev.magadiflo.app.command.CreateProductCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductCommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel request) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .title(request.getTitle())
                .productId(UUID.randomUUID().toString())
                .build();
        return this.commandGateway.sendAndWait(createProductCommand);
    }
}
