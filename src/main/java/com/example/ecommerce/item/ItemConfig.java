package com.example.ecommerce.item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ItemConfig {
    @Bean
    CommandLineRunner commandLineRunner2(ItemRepository itemRepository)
    {
        return args->{
            Item item1=new Item(1111,"new item",10.0,"picture",10);
            Item item2=new Item(2222,"new item",10.0,"picture",10);
            Item item3=new Item(3333,"new item",10.0,"picture",10);
        itemRepository.saveAll(List.of(item1,item2,item3));
        };
    }
}
