package org.tanvir.order.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tanvir.order.model.Order;
import org.tanvir.order.model.OrderItem;
import org.tanvir.order.repository.OrderRepository;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RestTemplate restTemplate;


    @Transactional
    public Order createOrder(Order order) {
        for (OrderItem item : order.getItems()) {
            String productUrl = "http://product-service:8082/api/products/" +
                    item.getProductId() + "/stock";
            ResponseEntity<Void> response = restTemplate.exchange(
                    productUrl,
                    HttpMethod.PUT,
                    null,
                    Void.class,
                    Map.of("quantity", item.getQuantity())
            );

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("Failed to update product stock");
            }
        }

        Order savedOrder = orderRepository.save(order);

        kafkaTemplate.send("order-events",
                "Order created: " + savedOrder.getId());

        return savedOrder;
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public void updateOrderStatus(Long orderId, String status) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setStatus(status);
            orderRepository.save(order);
            kafkaTemplate.send("order-events",
                    "Order status updated: " + orderId +
                            " - " + status);
        });
    }
}