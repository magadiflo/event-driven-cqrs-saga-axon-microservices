package dev.magadiflo.app.command.interceptors;

import dev.magadiflo.app.command.CreateProductCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

@Slf4j
@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (integer, commandMessage) -> {

            log.info("Comando interceptado: {}", commandMessage.getPayloadType());

            if (CreateProductCommand.class.equals(commandMessage.getPayloadType())) {

                CreateProductCommand payload = (CreateProductCommand) commandMessage.getPayload();

                if (payload.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("El precio no puede ser menor o igual a cero");
                }

                if (Objects.isNull(payload.getQuantity()) || payload.getQuantity() < 0) {
                    throw new IllegalArgumentException("La cantidad debe ser mayor o igual a cero");
                }

                if (Objects.isNull(payload.getTitle()) || payload.getTitle().isBlank()) {
                    throw new IllegalArgumentException("El título no puede estar vacío");
                }
            }

            return commandMessage;
        };
    }
}
