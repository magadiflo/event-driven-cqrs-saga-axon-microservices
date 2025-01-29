package dev.magadiflo.app.query.rest;

import dev.magadiflo.app.query.FindProductQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductQueryController {

    private final QueryGateway queryGateway;

    @GetMapping
    public ResponseEntity<List<ProductRestModel>> getProducts() {
        FindProductQuery findProductQuery = new FindProductQuery();

        List<ProductRestModel> products = this.queryGateway
                .query(findProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class))
                .join();

        return ResponseEntity.ok(products);
    }

}
