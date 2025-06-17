package org.example.backendwebshopnermin.exception;

/**
 * Kastas när en order försöker beställa fler enheter av en produkt
 * än vad som finns tillgängligt i lagret.
 */
public class InsufficientStockException extends RuntimeException {


    public InsufficientStockException(String productName) {
        super("Inte tillräckligt antal i lager för produkt: " + productName);
    }

}

