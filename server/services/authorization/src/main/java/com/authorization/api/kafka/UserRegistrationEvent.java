package com.authorization.api.kafka;

import com.authorization.model.enums.Role;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserRegistrationEvent {
    private Long userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private LocalDateTime registrationTime;
}