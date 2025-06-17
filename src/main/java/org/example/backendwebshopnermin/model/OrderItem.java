package org.example.backendwebshopnermin.model;

/**
 * Representerar en produkt i en order inklusive antal och pris vid köptillfället.
 */
public class OrderItem {

    // ID för den produkt som beställts
    private Long productId;

    // Antal som beställts
    private int quantity;

    // Pris för produkten vid beställningstillfället
    private double priceAtPurchase;


    public OrderItem() {}

    /**
     * Konstruktor som används när priset vid köptillfället inte är känt ännu.
     * Validerar att antal är > 0.
     */
    public OrderItem(Long productId, int quantity) {
        validateQuantity(quantity);
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Konstruktor som används vid skapande av ordern när priset också är känt.
     */
    public OrderItem(Long productId, int quantity, double priceAtPurchase) {
        validateQuantity(quantity);
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    // GETTERS & SETTERS

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    /**
     * Säkerställer att ett beställt antal är större än noll.
     */
    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Antal måste vara större än 0.");
        }
    }
}
