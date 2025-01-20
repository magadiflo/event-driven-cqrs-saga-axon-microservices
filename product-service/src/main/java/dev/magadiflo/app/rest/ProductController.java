package dev.magadiflo.app.rest;

import dev.magadiflo.app.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    private final Environment environment;

    @GetMapping
    public String getProduct() {
        return "[%s]HTTP GET Handled".formatted(this.environment.getProperty("local.server.port"));
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel request) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .title(request.getTitle())
                .productId(UUID.randomUUID().toString())
                .build();
        return "HTTP POST Handled " + request.getTitle();
    }

    @PutMapping
    public String updateProduct() {
        return "HTTP PUT Handled";
    }

    @DeleteMapping
    public String deleteProduct() {
        return "HTTP DELETE Handled";
    }
}
