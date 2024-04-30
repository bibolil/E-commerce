package com.example.ecommerce.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String country;
    private String date;
    private String address;
    private String email;
    private String phone;
}
