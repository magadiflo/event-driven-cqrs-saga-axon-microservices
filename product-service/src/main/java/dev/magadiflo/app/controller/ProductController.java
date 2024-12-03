package dev.magadiflo.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

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
    public String createProduct() {
        return "HTTP POST Handled";
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
