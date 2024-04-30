package com.example.ecommerce.auth;

import com.example.ecommerce.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String username;
    private String email;
    private  String password;
    private String address;
    private String country;
    private String phone;
    private String date;
    private Role role;
}
