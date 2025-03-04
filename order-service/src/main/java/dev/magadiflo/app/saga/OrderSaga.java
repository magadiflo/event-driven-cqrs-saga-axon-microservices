package dev.magadiflo.app.saga;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.spring.stereotype.Saga;

@RequiredArgsConstructor
@Saga
public class OrderSaga {

    private final transient CommandGateway commandGateway;
}
