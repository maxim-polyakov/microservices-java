package com.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApigatewayApplication {

    public static void main(String[] args) {
        // Исправлено: запускаем ApigatewayApplication.class
        SpringApplication.run(ApigatewayApplication.class, args);
    }
}