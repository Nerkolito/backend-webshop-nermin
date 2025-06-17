package org.example.backendwebshopnermin.repository;

import org.example.backendwebshopnermin.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Simulerar ett produktlager i minnet.
 */
@Repository
public class ProductRepository {

    // In-memory "databas" med produkter. Nyckel = produkt-ID, värde = Product-objekt.
    private final Map<Long, Product> productMap = new HashMap<>();


    public ProductRepository() {
        productMap.put(1L, new Product(1L, "Laptop", "Kraftfull laptop", 9999.0, "laptop.jpg", 5));
        productMap.put(2L, new Product(2L, "Mobil", "Smartphone med kamera", 4000.0, "mobil.jpg", 2));
        productMap.put(3L, new Product(3L, "TV", "4K SmartTV", 7000.0, "TV.jpg", 2));
    }

    /**
     * Hämtar produkt utifrån ID, returnerar Optional.
     */
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productMap.get(id));
    }


    public Optional<Product> getProductById(Long id) {
        return findById(id);
    }

    /**
     * Returnerar en lista med alla produkter.
     */
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    /**
     * Lägger till eller uppdaterar en produkt i lagret.
     */
    public void addProduct(Product product) {
        productMap.put(product.getId(), product);
    }
}
