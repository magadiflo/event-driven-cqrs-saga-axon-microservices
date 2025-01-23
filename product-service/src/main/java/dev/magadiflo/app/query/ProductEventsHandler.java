package dev.magadiflo.app.query;

import dev.magadiflo.app.core.event.ProductCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    @EventHandler
    public void on(ProductCreatedEvent event) {

    }
}
