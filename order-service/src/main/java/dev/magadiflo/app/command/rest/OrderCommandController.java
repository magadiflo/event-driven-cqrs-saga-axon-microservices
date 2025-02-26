package dev.magadiflo.app.command.rest;

import dev.magadiflo.app.command.CreateOrderCommand;
import dev.magadiflo.app.command.OrderStatus;
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
@RequestMapping(path = "/api/v1/orders")
public class OrderCommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String createOrder(@Valid @RequestBody CreateOrderRestModel request) {
        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .userId("27b95829-4f3f-4ddf-8983-151ba010e35b")
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .addressId(request.getAddressId())
                .orderStatus(OrderStatus.CREATED)
                .build();
        return this.commandGateway.sendAndWait(createOrderCommand);
    }

}
