package org.example.backendwebshopnermin.exception;

/**
 * Kastas när en produkt med angivet ID inte hittas i systemet.
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Skapar ett undantag med ett felmeddelande för en saknad produkt.
     */
    public ProductNotFoundException(Long productId) {
        super("Produkt med ID " + productId + " hittades inte.");
    }
}
