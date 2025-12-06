package com.consumer.service;

import com.consumer.api.kafka.UserRegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

    // Хранилище полученных событий (потокобезопасное)
    private final List<UserRegistrationEvent> receivedEvents = new CopyOnWriteArrayList<>();

    // Счётчик для демонстрации
    private int eventCount = 0;

    @KafkaListener(
        topics = "${kafka.topics.user-registration:user.registration.events}",
        groupId = "${spring.kafka.consumer.group-id:consumer-service-group}" // УНИКАЛЬНЫЙ!
    )
    public void consumeUserRegistration(UserRegistrationEvent event) {
        eventCount++;

        // Сохраняем событие для доступа через REST API
        receivedEvents.add(event);

        // Здесь ваша бизнес-логика
        processUserRegistration(event);
    }

    /**
     * Метод для контроллера: возвращает ВСЕ полученные события
     */
    public List<UserRegistrationEvent> getAllEvents() {
        return new ArrayList<>(receivedEvents); // Возвращаем копию
    }

    /**
     * Метод для контроллера: возвращает ПОСЛЕДНЕЕ полученное событие
     */
    public UserRegistrationEvent getLastEvent() {
        if (receivedEvents.isEmpty()) {
            return null;
        }
        return receivedEvents.get(receivedEvents.size() - 1);
    }

    public int getEventCount() {
        return eventCount;
    }

    private void processUserRegistration(UserRegistrationEvent event) {
        // Ваша бизнес-логика здесь
        log.debug("Обработка регистрации пользователя ID: {}", event.getUserId());
    }
}