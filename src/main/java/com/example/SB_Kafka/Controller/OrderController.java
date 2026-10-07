package com.example.SB_Kafka.Controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public String createOrder() {
        try {
            kafkaTemplate.send("order-events", "Order 1001 created")
                    .get();

            System.out.println("MESSAGE SENT");
            return "Order created";

        } catch (Exception ex) {
            System.out.println("MESSAGE FAILED: " + ex.getMessage());
            return "Order could not be created because Kafka is unavailable";
        }
    }
}