package dev.magadiflo.app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    @GetMapping
    public String getProduct() {
        return "HTTP GET Handled";
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
