package com.example.ecommerce.user;

import com.example.ecommerce.auth.AuthenticationService;
import com.example.ecommerce.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.ecommerce.user.Role.ADMIN;
import static com.example.ecommerce.user.Role.USER;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(AuthenticationService service) {

        return args -> {
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@mail.com")
                    .username("admin")
                    .password("admin")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getToken());
            var user = RegisterRequest.builder()
                    .firstname("Bilel")
                    .lastname("Taboubi")
                    .email("bilel@mail.com")
                    .username("bilel")
                    .password("bilel")
                    .role(USER)
                    .build();
            System.out.println("User token: " + service.register(user).getToken());
        };
}}

