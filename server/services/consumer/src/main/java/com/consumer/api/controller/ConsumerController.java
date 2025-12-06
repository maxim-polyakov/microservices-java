package com.consumer.api.controller;

import com.consumer.api.kafka.UserRegistrationEvent;
import com.consumer.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @GetMapping("/events")
    public ResponseEntity<List<UserRegistrationEvent>> getAllEvents() {
        List<UserRegistrationEvent> events = consumerService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/last")
    public ResponseEntity<?> getLastEvent() {
        UserRegistrationEvent lastEvent = consumerService.getLastEvent();

        if (lastEvent == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Событий из Kafka еще не получено");
            response.put("status", "EMPTY");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.ok(lastEvent);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalEventsReceived", consumerService.getEventCount());
        stats.put("eventsInMemory", consumerService.getAllEvents().size());
        stats.put("service", "Kafka Consumer");
        stats.put("topic", "user.registration.events");

        return ResponseEntity.ok(stats);
    }

    @DeleteMapping("/events/clear")
    public ResponseEntity<Map<String, String>> clearEvents() {
        // В реальном сервисе так делать не нужно, это только для демонстрации
        Map<String, String> response = new HashMap<>();
        response.put("message", "Метод clear не реализован в этой версии");
        response.put("hint", "События хранятся в памяти и очистятся при перезапуске сервиса");
        return ResponseEntity.ok(response);
    }
}