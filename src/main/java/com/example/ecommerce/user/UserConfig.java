package com.example.ecommerce.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository)
    {
        return args->{
//            User bilel= new User("Bilel Taboubi",LocalDate.of(2000, 12,18),"bt@gmail.com","bileltb20","123465789","Bardo","+216123456",Role.USER);
//            User iheb=new User("Iheb Etteyeb",LocalDate.of(2000, 12,18),"it@gmail.com","iheb20","123465789","Bardo","+216123456",Role.USER);
////            User salma= new User("Salma Nebli",LocalDate.of(2000, 12,18),"sn@gmail.com","salma20","123465789","Bardo","+216123456",Role.USER);
//            User aziz= new User("Aziz kurlex",LocalDate.of(2000, 12,18),"ak@gmail.com","aziz20","123465789","Bardo","+216123456",Role.USER);
            repository.saveAll(List.of());
        };

    }

}
