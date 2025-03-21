package dev.magadiflo.app.query;

import com.magadiflo.core.app.events.ProductReservedEvent;
import dev.magadiflo.app.core.data.ProductEntity;
import dev.magadiflo.app.core.data.ProductRepository;
import dev.magadiflo.app.core.event.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@ProcessingGroup("product-group")
@Component
public class ProductEventsHandler {

    private final ProductRepository productRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        log.info("A partir del ProductCreatedEvent crea el ProductEntity para guardar en la base de datos.");
        ProductEntity productEntity = ProductEntity.builder()
                .productId(event.getProductId())
                .title(event.getTitle())
                .price(event.getPrice())
                .quantity(event.getQuantity())
                .build();

        this.productRepository.save(productEntity);
    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        Optional<ProductEntity> optionalProductEntity = this.productRepository.findByProductId(productReservedEvent.getProductId());
        if (optionalProductEntity.isPresent()) {
            ProductEntity productEntity = optionalProductEntity.get();
            productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());
            this.productRepository.save(productEntity);
        }
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException exception) {
        log.error("Ocurrió un error: {}. Volvemos a lanzar la excepción", exception.getMessage());
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception {
        log.error("Ocurrió un error general: {}. Volvemos a lanzar la excepción", exception.getMessage());
        throw exception;
    }
}
