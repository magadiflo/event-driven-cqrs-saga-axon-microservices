package dev.magadiflo.app.command;

import com.magadiflo.core.app.commands.ReserveProductCommand;
import dev.magadiflo.app.core.event.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@NoArgsConstructor
@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio no puede ser menor o igual a cero");
        }

        if (Objects.isNull(createProductCommand.getQuantity()) || createProductCommand.getQuantity() < 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor o igual a cero");
        }

        if (Objects.isNull(createProductCommand.getTitle()) || createProductCommand.getTitle().isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        productCreatedEvent.setProductId(createProductCommand.getProductId());
        productCreatedEvent.setTitle(createProductCommand.getTitle());
        productCreatedEvent.setPrice(createProductCommand.getPrice());
        productCreatedEvent.setQuantity(createProductCommand.getQuantity());

        AggregateLifecycle.apply(productCreatedEvent);
    }

    @CommandHandler
    public void handle(ReserveProductCommand reserveProductCommand) {
        if (this.quantity < reserveProductCommand.getQuantity()) {
            throw new IllegalArgumentException("Insufficient number of items in stock");
        }
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        log.info("Asigna valores de las propiedades del ProductCreatedEvent a las propiedades del ProductAggregate");
        this.productId = event.getProductId();
        this.title = event.getTitle();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
    }

}
