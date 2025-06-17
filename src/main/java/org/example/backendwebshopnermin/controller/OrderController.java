package org.example.backendwebshopnermin.controller;

import org.example.backendwebshopnermin.dto.OrderResponse;
import org.example.backendwebshopnermin.model.CustomerInfo;
import org.example.backendwebshopnermin.model.Order;
import org.example.backendwebshopnermin.model.OrderItem;
import org.example.backendwebshopnermin.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Skapar en ny order baserat på JSON-data från användaren.
     * Kräver att både kundinformation och en lista med orderrader skickas in.
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> placeOrder(@RequestBody Map<String, Object> orderRequest) {
        @SuppressWarnings("unchecked")
        Map<String, Object> customerMap = (Map<String, Object>) orderRequest.get("customerInfo");

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> itemsMap = (List<Map<String, Object>>) orderRequest.get("items");

        // Validera att båda delar finns i requesten
        if (customerMap == null || itemsMap == null) {
            throw new IllegalArgumentException("Requesten måste innehålla 'customerInfo' och 'items'");
        }

        // Skapa kundobjekt från JSON
        CustomerInfo customerInfo = new CustomerInfo(
                (String) customerMap.get("name"),
                (String) customerMap.get("address"),
                (String) customerMap.get("email")
        );

        // Konvertera listan av produkter från JSON till Java-objekt
        List<OrderItem> items = itemsMap.stream()
                .map(item -> {
                    long productId = ((Number) item.get("productId")).longValue();
                    int quantity = ((Number) item.get("quantity")).intValue();

                    // Säkerställ att antalet är större än noll
                    if (quantity <= 0) {
                        throw new IllegalArgumentException("Antalet måste vara större än 0 för produkt: " + productId);
                    }

                    return new OrderItem(productId, quantity);
                })
                .collect(Collectors.toList());

        // Skapa order via OrderService
        Order order = orderService.createOrder(customerInfo, items);

        // Returnera svar med HTTP 201
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "orderId", order.getId(),
                        "message", "Tack för din beställning!"
                )
        );
    }

    /**
     * Hämtar en specifik order baserat på order-ID.
     */
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable String id) {
        return toDto(orderService.getOrderByIdOrThrow(id));
    }

    /**
     * Hämtar alla ordrar i systemet.
     */
    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Raderar en order baserat på ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(Map.of("message", "Order raderades"));
    }

    /**
     * Hämtar alla ordrar som tillhör en specifik kund.
     */
    @GetMapping("/customer/{name}")
    public List<OrderResponse> getOrdersByCustomer(@PathVariable String name) {
        return orderService.getOrdersByCustomerName(name).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Hjälpmetod för att konvertera en Order till OrderResponse.
     */
    private OrderResponse toDto(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerInfo(),
                order.getItems(),
                order.getTotalAmount(),
                order.getOrderDate()
        );
    }
}
