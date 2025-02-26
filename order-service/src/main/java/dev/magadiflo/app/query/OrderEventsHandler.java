package dev.magadiflo.app.query;

import dev.magadiflo.app.core.data.OrderEntity;
import dev.magadiflo.app.core.data.OrderRepository;
import dev.magadiflo.app.core.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventsHandler {

    private final OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("Creando el objeto OrderEntity");
        OrderEntity orderEntity = OrderEntity.builder()
                .orderId(event.getOrderId())
                .productId(event.getProductId())
                .userId(event.getUserId())
                .quantity(event.getQuantity())
                .addressId(event.getAddressId())
                .orderStatus(event.getOrderStatus())
                .build();
        this.orderRepository.save(orderEntity);
        log.info("Objeto OrderEntity guardado en base de datos");
    }
}
