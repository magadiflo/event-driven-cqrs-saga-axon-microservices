package dev.magadiflo.app.command;

import dev.magadiflo.app.core.data.ProductLookupEntity;
import dev.magadiflo.app.core.data.ProductLookupRepository;
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
public class ProductLookupEventsHandler {

    private final ProductLookupRepository productLookupRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) {
        log.info("A partir del ProductCreatedEvent crea el ProductLookupEntity y guarda en la tabla de base de datos de búsqueda.");
        ProductLookupEntity productLookupEntity = ProductLookupEntity.builder()
                .productId(event.getProductId())
                .title(event.getTitle())
                .build();

        this.productLookupRepository.save(productLookupEntity);
    }

}
