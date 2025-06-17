package org.example.backendwebshopnermin.exception;

/**
 * Kastas när en order med angivet ID inte hittas i systemet.
 */
public class OrderNotFoundException extends RuntimeException {

    /**
     * Skapar ett undantag med ett meddelande som inkluderar order-ID.
     *
     */
    public OrderNotFoundException(String orderId) {
        super("Order med ID " + orderId + " hittades inte.");
    }
}
