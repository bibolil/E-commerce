package com.example.ecommerce.auth;

import com.example.ecommerce.config.JwtService;
import com.example.ecommerce.user.Role;
import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService
{
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    public AuthenticationResponse register(RegisterRequest request) {
        //to-do add dob address mobilenum
    var user= User.builder().firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
    repository.save(user);
    var jwtToken=jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user= repository.findUserByEmail(request.getEmail()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
