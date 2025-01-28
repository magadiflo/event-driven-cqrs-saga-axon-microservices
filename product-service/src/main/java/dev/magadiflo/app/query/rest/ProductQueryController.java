package dev.magadiflo.app.query.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductQueryController {

    @GetMapping
    public ResponseEntity<List<ProductRestModel>> getProducts() {
        return null;
    }

}
