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
                    .name("Admin")
                    .email("admin@mail.com")
                    .username("admin")
                    .password("admin")
                    .phone("123456789")
                    .address("bardo")
                    .country("Tunisia")
                    .date("2018-03-21")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());
            var user = RegisterRequest.builder()
                    .name("Bilel")
                    .email("bilel@mail.com")
                    .username("bilel")
                    .password("bilel")
                    .phone("123456789")
                    .address("bardo")
                    .country("Tunisia")
                    .date("2018-03-21")
                    .role(USER)
                    .build();
            System.out.println("User token: " + service.register(user).getAccessToken());
            // Additional users
            var sarah = RegisterRequest.builder()
                    .name("Sarah")
                    .email("sarah@mail.com")
                    .username("sarah")
                    .password("password123")
                    .phone("987654321")
                    .address("ariana")
                    .country("Tunisia")
                    .date("2018-03-21")
                    .role(USER)
                    .build();
            System.out.println("User token: " + service.register(sarah).getAccessToken());

            var mark = RegisterRequest.builder()
                    .name("Mark")
                    .email("mark@mail.com")
                    .username("mark")
                    .password("password456")
                    .phone("654321987")
                    .address("marsa")
                    .country("Tunisia")
                    .date("2018-03-21")
                    .role(USER)
                    .build();
            System.out.println("User token: " + service.register(mark).getAccessToken());

            var jessica = RegisterRequest.builder()
                    .name("Jessica")
                    .email("jessica@mail.com")
                    .username("jessica")
                    .password("password789")
                    .phone("321654987")
                    .address("tunis")
                    .country("Tunisia")
                    .date("2018-03-21")
                    .role(USER)
                    .build();
            System.out.println("User token: " + service.register(jessica).getAccessToken());

            // New users
            var emily = RegisterRequest.builder()
                    .name("Emily")
                    .email("emily@mail.com")
                    .username("emily")
                    .password("emily2023")
                    .phone("432198765")
                    .address("sfax")
                    .country("Tunisia")
                    .date("2018-03-21")
                    .role(USER)
                    .build();
            System.out.println("User token: " + service.register(emily).getAccessToken());

            var daniel = RegisterRequest.builder()
                    .name("Daniel")
                    .email("daniel@mail.com")
                    .username("daniel")
                    .password("dani2023")
                    .phone("521467890")
                    .address("monastir")
                    .country("Tunisia")
                    .date("2018-03-21")
                    .role(USER)
                    .build();
            System.out.println("User token: " + service.register(daniel).getAccessToken());

            var sophia = RegisterRequest.builder()
                    .name("Sophia")
                    .email("sophia@mail.com")
                    .username("sophia")
                    .password("sophie2023")
                    .phone("690123456")
                    .address("nabeul")
                    .country("Tunisia")
                    .date("2018-03-21")
                    .role(USER)
                    .build();
            System.out.println("User token: " + service.register(sophia).getAccessToken());
        };


    }
}

