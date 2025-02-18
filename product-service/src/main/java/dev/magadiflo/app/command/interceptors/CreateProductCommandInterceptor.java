package dev.magadiflo.app.command.interceptors;

import dev.magadiflo.app.command.CreateProductCommand;
import dev.magadiflo.app.core.data.ProductLookupEntity;
import dev.magadiflo.app.core.data.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookupRepository productLookupRepository;

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (integer, commandMessage) -> {

            log.info("Comando interceptado: {}", commandMessage.getPayloadType());

            if (CreateProductCommand.class.equals(commandMessage.getPayloadType())) {

                CreateProductCommand payload = (CreateProductCommand) commandMessage.getPayload();

                Optional<ProductLookupEntity> optionalProductLookup = this.productLookupRepository.findByProductIdOrTitle(payload.getProductId(), payload.getTitle());
                if (optionalProductLookup.isPresent()) {
                    throw new IllegalStateException("Product with productId %s or title %s already exist".formatted(payload.getProductId(), payload.getTitle()));
                }

            }

            return commandMessage;
        };
    }
}
