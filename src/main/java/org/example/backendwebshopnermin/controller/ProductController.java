package org.example.backendwebshopnermin.controller;


import org.example.backendwebshopnermin.model.Product;
import org.example.backendwebshopnermin.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-kontroller för att hantera produktrelaterade API-anrop.
 * Hanterar hämtning av alla produkter samt enskilda produkter via ID.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // Injektion av ProductService
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * GET /api/products
     * Returnerar en lista över alla produkter som finns i systemet.
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * GET /api/products/{id}
     * Returnerar en specifik produkt baserat på ID.
     */
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductByIdOrThrow(id);
    }
}
