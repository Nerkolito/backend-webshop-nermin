package org.example.backendwebshopnermin.repository;

import org.example.backendwebshopnermin.model.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Använder en HashMap
 */
@Repository
public class OrderRepository {


    private final Map<String, Order> orderStorage = new HashMap<>();

    /**
     * Sparar en order i lagringen.
     */
    public void save(String id, Order order) {
        orderStorage.put(id, order);
    }

    /**
     * Hämtar en order baserat på ID.
     */
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orderStorage.get(id));
    }

    /**
     * Returnerar alla sparade ordrar som en lista.
     */
    public List<Order> findAll() {
        return new ArrayList<>(orderStorage.values());
    }

    /**
     * Tar bort en order från lagringen.
     */
    public void deleteById(String id) {
        orderStorage.remove(id);
    }

    /**
     * Söker efter ordrar baserat på kundens namn.
     */
    public List<Order> findByCustomerName(String name) {
        return orderStorage.values().stream()
                .filter(order -> order.getCustomerInfo().getName().equalsIgnoreCase(name))
                .toList(); // Java 16+ – annars använd collect(Collectors.toList())
    }
}
