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
            List<Item> items = List.of(
                    new Item("f230fh0g3", "Bamboo Watch", "Product Description", "bamboo-watch.jpg", 65.0, "Accessories", 24, "INSTOCK", 5),
                    new Item("nvklal433", "Black Watch", "Product Description", "black-watch.jpg", 72.0, "Accessories", 61, "INSTOCK", 4),
                    new Item( "zz21cz3c1", "Blue Band", "Product Description", "blue-band.jpg", 79.0, "Fitness", 2, "LOWSTOCK", 3),
                    new Item("244wgerg2", "Blue T-Shirt", "Product Description", "blue-t-shirt.jpg", 29.0, "Clothing", 25, "INSTOCK", 5),
                    new Item( "h456wer53", "Bracelet", "Product Description", "bracelet.jpg", 15.0, "Accessories", 73, "INSTOCK", 4),
                    new Item( "av2231fwg", "Brown Purse", "Product Description", "brown-purse.jpg", 120.0, "Accessories", 0, "OUTOFSTOCK", 4),
                    new Item("bib36pfvm", "Chakra Bracelet", "Product Description", "chakra-bracelet.jpg", 32.0, "Accessories", 5, "LOWSTOCK", 3),
                    new Item("mbvjkgip5", "Galaxy Earrings", "Product Description", "galaxy-earrings.jpg", 34.0, "Accessories", 23, "INSTOCK", 5),
                    new Item("vbb124btr", "Game Controller", "Product Description", "game-controller.jpg", 99.0, "Electronics", 2, "LOWSTOCK", 4),
                    new Item("cm230f032", "Gaming Set", "Product Description", "gaming-set.jpg", 299.0, "Electronics", 63, "INSTOCK", 3),
                    new Item("plb34234v", "Gold Phone Case", "Product Description", "gold-phone-case.jpg", 24.0, "Accessories", 0, "OUTOFSTOCK", 4),
                    new Item("4920nnc2d", "Green Earbuds", "Product Description", "green-earbuds.jpg", 89.0, "Electronics", 23, "INSTOCK", 4),
                    new Item("250vm23cc", "Green T-Shirt", "Product Description", "green-t-shirt.jpg", 49.0, "Clothing", 74, "INSTOCK", 5),
                    new Item("fldsmn31b", "Grey T-Shirt", "Product Description", "grey-t-shirt.jpg", 48.0, "Clothing", 0, "OUTOFSTOCK", 3),
                    new Item("waas1x2as", "Headphones", "Product Description", "headphones.jpg", 175.0, "Electronics", 8, "LOWSTOCK", 5),
                    new Item("vb34btbg5", "Light Green T-Shirt", "Product Description", "light-green-t-shirt.jpg", 49.0, "Clothing", 34, "INSTOCK", 4),
                    new Item( "k8l6j58jl", "Lime Band", "Product Description", "lime-band.jpg", 79.0, "Fitness", 12, "INSTOCK", 3),
                    new Item("v435nn85n", "Mini Speakers", "Product Description", "mini-speakers.jpg", 85.0, "Clothing", 42, "INSTOCK", 4),
                    new Item("09zx9c0zc", "Painted Phone Case", "Product Description", "painted-phone-case.jpg", 56.0, "Accessories", 41, "INSTOCK", 5),
                    new Item("mnb5mb2m5", "Pink Band", "Product Description", "pink-band.jpg", 79.0, "Fitness", 63, "INSTOCK", 4),
                    new Item("r23fwf2w3", "Pink Purse", "Product Description", "pink-purse.jpg", 110.0, "Accessories", 0, "OUTOFSTOCK", 4),
                    new Item("pxpzczo23", "Purple Band", "Product Description", "purple-band.jpg", 79.0, "Fitness", 6, "LOWSTOCK", 3),
                    new Item("2c42cb5cb", "Purple Gemstone Necklace", "Product Description", "purple-gemstone-necklace.jpg", 45.0, "Accessories", 62, "INSTOCK", 4),
                    new Item("5k43kkk23", "Purple T-Shirt", "Product Description", "purple-t-shirt.jpg", 49.0, "Clothing", 2, "LOWSTOCK", 5),
                    new Item("lm2tny2k4", "Shoes", "Product Description", "shoes.jpg", 64.0, "Clothing", 0, "INSTOCK", 4),
                    new Item("nbm5mv45n", "Sneakers", "Product Description", "sneakers.jpg", 78.0, "Clothing", 52, "INSTOCK", 4),
                    new Item("zx23zc42c", "Teal T-Shirt", "Product Description", "teal-t-shirt.jpg", 49.0, "Clothing", 3, "LOWSTOCK", 3),
                    new Item("acvx872gc", "Yellow Earbuds", "Product Description", "yellow-earbuds.jpg", 89.0, "Electronics", 35, "INSTOCK", 3),
                    new Item("tx125ck42", "Yoga Mat", "Product Description", "yoga-mat.jpg", 20.0, "Fitness", 15, "INSTOCK", 5),
                    new Item("gwuby345v", "Yoga Set", "Product Description", "yoga-set.jpg", 20.0, "Fitness", 25, "INSTOCK", 8));
        itemRepository.saveAll(items);
        };
    }
}
