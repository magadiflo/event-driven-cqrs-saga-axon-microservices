package dev.magadiflo.app.command;

import dev.magadiflo.app.core.event.OrderCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@NoArgsConstructor
@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        orderCreatedEvent.setOrderId(createOrderCommand.getOrderId());
        orderCreatedEvent.setProductId(createOrderCommand.getProductId());
        orderCreatedEvent.setUserId(createOrderCommand.getUserId());
        orderCreatedEvent.setQuantity(createOrderCommand.getQuantity());
        orderCreatedEvent.setAddressId(createOrderCommand.getAddressId());
        orderCreatedEvent.setOrderStatus(createOrderCommand.getOrderStatus());

        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        log.info("Asigna valores de las propiedades del OrderCreatedEvent a las propiedades del OrderAggregate");
        this.orderId = event.getOrderId();
        this.productId = event.getProductId();
        this.userId = event.getUserId();
        this.quantity = event.getQuantity();
        this.addressId = event.getAddressId();
        this.orderStatus = event.getOrderStatus();
    }

}
