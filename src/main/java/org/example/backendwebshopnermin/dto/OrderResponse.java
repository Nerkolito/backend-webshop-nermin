package org.example.backendwebshopnermin.dto;

import org.example.backendwebshopnermin.model.CustomerInfo;
import org.example.backendwebshopnermin.model.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO för att returnera ordersvar till klienten.
 * Används i API-svar för att representera en order på ett tydligt och strukturerat sätt.
 */
public class OrderResponse {

    private String id;                      // Unikt order-ID
    private CustomerInfo customerInfo;     // Kundens information (namn, adress, e-post)
    private List<OrderItem> items;         // Lista med beställda produkter (med antal och pris)
    private double totalAmount;            // Totalsumma för ordern
    private LocalDateTime orderDate;       // Tidpunkt då ordern skapades

    /**
     * Konstruktor som sätter alla fält.
     * Används när en Order ska konverteras till en OrderResponse.
     */
    public OrderResponse(String id, CustomerInfo customerInfo, List<OrderItem> items, double totalAmount, LocalDateTime orderDate) {
        this.id = id;
        this.customerInfo = customerInfo;
        this.items = items;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    // Getters – används av JSON-serialisering
    public String getId() {
        return id;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
