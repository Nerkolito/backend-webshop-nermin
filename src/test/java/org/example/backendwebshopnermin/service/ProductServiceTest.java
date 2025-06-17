package org.example.backendwebshopnermin.service;

import org.example.backendwebshopnermin.exception.ProductNotFoundException;
import org.example.backendwebshopnermin.model.Product;
import org.example.backendwebshopnermin.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester för logiken i ProductService
 */
public class ProductServiceTest {

    private ProductService productService;

    /**
     * Initierar tjänsten med ett förifyllt repo.
     */
    @BeforeEach
    public void setup() {
        ProductRepository productRepository = new ProductRepository(); // laddar med 3 produkter
        productService = new ProductService(productRepository);
    }

    @Test
    public void testGetAllProducts() {
        //  Verifierar att alla produkter hämtas korrekt
        List<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertTrue(products.size() >= 2); // minst 2 produkter förväntas från repo
    }

    @Test
    public void testGetProductByIdFound() {
        //  Hämtar produkt med ID 1 – ska finnas
        Optional<Product> product = productService.getProductById(1L);

        assertTrue(product.isPresent());
        assertEquals(1L, product.get().getId());
        assertEquals("Laptop", product.get().getName());
    }

    @Test
    public void testGetProductByIdNotFound() {
        //  Testar att produkt som inte finns
        Optional<Product> product = productService.getProductById(999L);

        assertFalse(product.isPresent());
    }

    @Test
    public void testGetProductByIdOrThrowFound() {
        //  Hämtar produkt med fallback – kastar ej exception
        Product product = productService.getProductByIdOrThrow(1L);

        assertEquals("Laptop", product.getName());
    }

    @Test
    public void testGetProductByIdOrThrowNotFound() {
        //  Hämtar med fallback men ID finns inte – kastar exception
        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductByIdOrThrow(999L);
        });
    }
}
