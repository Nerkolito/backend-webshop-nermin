package org.example.backendwebshopnermin.service;

import org.example.backendwebshopnermin.exception.ProductNotFoundException;
import org.example.backendwebshopnermin.model.Product;
import org.example.backendwebshopnermin.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Tjänstlager som ansvarar för affärslogik kring produkter.
 * Används som mellanlager mellan Controller och Repository.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Dependency Injection via konstruktorn
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Hämtar produkt som Optional.
     * Används när man vill hantera tomt resultat på egen hand.
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Hämtar produkt eller kastar ett undantag om den inte finns.
     * Används för enklare flöde där man inte vill hantera Optional själv.
     */
    public Product getProductByIdOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Returnerar alla produkter i lagret.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
