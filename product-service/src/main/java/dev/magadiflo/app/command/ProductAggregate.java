package dev.magadiflo.app.command;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

@NoArgsConstructor
@Aggregate
public class ProductAggregate {

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        // validate create product command
    }

}
