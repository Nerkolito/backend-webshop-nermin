package org.example.backendwebshopnermin.service;

import org.example.backendwebshopnermin.model.*;
import org.example.backendwebshopnermin.repository.OrderRepository;
import org.example.backendwebshopnermin.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester för OrderService-logik
 */
public class OrderServiceTest {

    private OrderService orderService;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        // 🧪 Sätt upp testdata och repositories
        productRepository = new ProductRepository();
        orderRepository = new OrderRepository();
        productRepository.addProduct(new Product(1L, "Testprodukt", "Beskrivning", 99.99, "bild.jpg", 5));

        // 🛠️ Injektera både ProductRepository och OrderRepository
        orderService = new OrderService(productRepository, orderRepository);
    }

    @Test
    public void testCreateOrderSuccessfully() {
        CustomerInfo customer = new CustomerInfo("Anna", "Testvägen 1", "anna@example.com");
        List<OrderItem> items = List.of(new OrderItem(1L, 2));

        Order order = orderService.createOrder(customer, items);

        assertNotNull(order.getId());
        assertEquals(1, order.getItems().size());
        assertEquals(199.98, order.getTotalAmount(), 0.01);
        assertEquals("Anna", order.getCustomerInfo().getName());
    }

    @Test
    public void testStockReducesAfterOrder() {
        Product productBefore = productRepository.getProductById(1L).orElseThrow();
        int stockBefore = productBefore.getStock();

        CustomerInfo customer = new CustomerInfo("Kalle", "Gata 123", "kalle@example.com");
        List<OrderItem> items = List.of(new OrderItem(1L, 3));

        orderService.createOrder(customer, items);

        Product productAfter = productRepository.getProductById(1L).orElseThrow();
        int stockAfter = productAfter.getStock();

        assertEquals(stockBefore - 3, stockAfter);
    }

    @Test
    public void testOrderFailsWhenStockIsTooLow() {
        CustomerInfo customer = new CustomerInfo("Lotta", "Gatan 234", "lotta@example.com");
        List<OrderItem> items = List.of(new OrderItem(1L, 99)); // för mycket

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(customer, items);
        });

        assertTrue(exception.getMessage().contains("Inte tillräckligt antal i lager"));
    }

    @Test
    public void testStockRestoresAfterOrderDeletion() {
        Product productBefore = productRepository.getProductById(1L).orElseThrow();
        int stockBefore = productBefore.getStock();

        CustomerInfo customer = new CustomerInfo("Eva", "Gatan 567", "eva@example.com");
        List<OrderItem> items = List.of(new OrderItem(1L, 2));

        Order order = orderService.createOrder(customer, items);

        orderService.deleteOrder(order.getId());

        Product productAfter = productRepository.getProductById(1L).orElseThrow();
        int stockAfter = productAfter.getStock();

        assertEquals(stockBefore, stockAfter, "Lagret ska återställas efter att ordern raderas");
    }
}
