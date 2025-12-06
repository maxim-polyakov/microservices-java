package com.authorization.service.kafka;

import com.authorization.api.kafka.UserRegistrationEvent;
import com.authorization.model.LocalUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, UserRegistrationEvent> kafkaTemplate;

    @Value("${kafka.topics.user-registration:user.registration.events}")
    private String userRegistrationTopic;

    public void sendUserRegistrationEvent(LocalUser user) {
        try {
            UserRegistrationEvent event = createEventFromUser(user);

            // Отправляем сообщение в Kafka
            CompletableFuture<SendResult<String, UserRegistrationEvent>> future =
                kafkaTemplate.send(userRegistrationTopic, user.getEmail(), event);

            // Обрабатываем результат асинхронно
            future.whenComplete((result, ex) -> {
            });

        } catch (Exception e) {
        }
    }

    private UserRegistrationEvent createEventFromUser(LocalUser user) {
        UserRegistrationEvent event = new UserRegistrationEvent();
        event.setUserId(user.getId());
        event.setUsername(user.getUsername());
        event.setEmail(user.getEmail());
        event.setFirstName(user.getFirstName());
        event.setLastName(user.getLastName());
        event.setRole(user.getRole().name());
        event.setRegistrationTime(LocalDateTime.now());
        return event;
    }
}