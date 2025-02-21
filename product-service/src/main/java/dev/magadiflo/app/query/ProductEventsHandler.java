package dev.magadiflo.app.query;

import dev.magadiflo.app.core.data.ProductEntity;
import dev.magadiflo.app.core.data.ProductRepository;
import dev.magadiflo.app.core.event.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@ProcessingGroup("product-group")
@Component
public class ProductEventsHandler {

    private final ProductRepository productRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) {
        log.info("A partir del ProductCreatedEvent crea el ProductEntity para guardar en la base de datos.");
        ProductEntity productEntity = ProductEntity.builder()
                .productId(event.getProductId())
                .title(event.getTitle())
                .price(event.getPrice())
                .quantity(event.getQuantity())
                .build();

        this.productRepository.save(productEntity);
    }
}
