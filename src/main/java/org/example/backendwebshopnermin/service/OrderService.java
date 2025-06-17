package org.example.backendwebshopnermin.service;

import org.example.backendwebshopnermin.exception.InsufficientStockException;
import org.example.backendwebshopnermin.exception.OrderNotFoundException;
import org.example.backendwebshopnermin.exception.ProductNotFoundException;
import org.example.backendwebshopnermin.model.*;
import org.example.backendwebshopnermin.repository.OrderRepository;
import org.example.backendwebshopnermin.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    private final ProductRepository productRepository; // Lagerhantering
    private final OrderRepository orderRepository;     // Orderlagring

    // Dependency Injection av repositories
    public OrderService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Skapar en ny order.
     * - Validerar produkter
     * - Minskar lagersaldo
     * - Beräknar totalbelopp
     * - Sparar ordern i minnet
     */
    public Order createOrder(CustomerInfo customerInfo, List<OrderItem> items) {
        List<OrderItem> validatedItems = new ArrayList<>();
        double total = 0;

        for (OrderItem item : items) {
            // Hämta produkten eller kasta fel om den inte finns
            Product product = productRepository.getProductById(item.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(item.getProductId()));

            // Kontrollera att tillräckligt många finns i lager
            if (product.getStock() < item.getQuantity()) {
                throw new InsufficientStockException(product.getName());
            }

            // Minska lagersaldo
            product.setStock(product.getStock() - item.getQuantity());

            // Beräkna totalsumma
            double itemTotal = product.getPrice() * item.getQuantity();
            total += itemTotal;

            // Lägg till validerat orderitem
            validatedItems.add(new OrderItem(
                    item.getProductId(),
                    item.getQuantity(),
                    product.getPrice()
            ));
        }

        // Skapa ett unikt order-ID
        String orderId = UUID.randomUUID().toString();

        // Skapa orderobjekt med tidpunkt
        Order order = new Order(orderId, customerInfo, validatedItems, total, LocalDateTime.now());

        // Spara i repository
        orderRepository.save(orderId, order);

        return order;
    }

    /**
     * Hämta en specifik order eller kasta fel om den inte finns.
     */
    public Order getOrderByIdOrThrow(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    /**
     * Returnerar alla sparade ordrar.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Raderar en order och återställer lagersaldo för varje produkt.
     */
    public void deleteOrder(String id) {
        // Hämta ordern
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        // Återställ stock
        for (OrderItem item : order.getItems()) {
            productRepository.getProductById(item.getProductId()).ifPresent(product ->
                    product.setStock(product.getStock() + item.getQuantity()));
        }

        // Ta bort från lagring
        orderRepository.deleteById(id);
    }

    /**
     * Returnerar ordrar baserat på kundnamn.
     * Kastar fel om ingen order hittas.
     */
    public List<Order> getOrdersByCustomerName(String name) {
        List<Order> result = orderRepository.findByCustomerName(name);
        if (result.isEmpty()) {
            throw new OrderNotFoundException("Ingen order hittades för kundnamn: " + name);
        }
        return result;
    }
}
