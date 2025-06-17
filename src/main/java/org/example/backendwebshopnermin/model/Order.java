package org.example.backendwebshopnermin.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representerar en kundorder i webshoppen.
 * Innehåller information om kunden, beställda produkter, totalbelopp och orderdatum.
 */
public class Order {

    // Unikt ID för ordern
    private String id;

    // Kundinformation kopplad till ordern
    private CustomerInfo customerInfo;

    // Lista över produkter och antal i ordern
    private List<OrderItem> items;

    // Totala summan för ordern vid köptillfället
    private double totalAmount;

    // Datum och tid då ordern skapades
    private LocalDateTime orderDate;

    /**
     * Tom konstruktor krävs för Spring/JSON-deserialisering
     */
    public Order() {}

    /**
     * Fullständig konstruktor – används för att skapa en ny order.
     */
    public Order(String id, CustomerInfo customerInfo, List<OrderItem> items, double totalAmount, LocalDateTime orderDate) {
        this.id = id;
        this.customerInfo = customerInfo;
        this.items = items;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    //  GETTERS & SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
