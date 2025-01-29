package dev.magadiflo.app.query;

import dev.magadiflo.app.core.data.ProductRepository;
import dev.magadiflo.app.query.rest.ProductRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductQuery query) {
        return this.productRepository.findAll().stream()
                .map(productEntity -> {
                    ProductRestModel productRestModel = new ProductRestModel();
                    productRestModel.setProductId(productEntity.getProductId());
                    productRestModel.setQuantity(productEntity.getQuantity());
                    productRestModel.setPrice(productEntity.getPrice());
                    productRestModel.setTitle(productEntity.getTitle());
                    return productRestModel;
                })
                .toList();
    }
}
