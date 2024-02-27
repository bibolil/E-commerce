package com.example.ecommerce.auth;

import com.example.ecommerce.config.JwtService;
import com.example.ecommerce.user.Role;
import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        //Checking if username or mail are being in use
        var username= repository.findUserByUsername(request.getUsername());
        var email= repository.findUserByEmail(request.getEmail());
        if(username.isPresent())
        {
            return AuthenticationResponse.builder().status(HttpStatus.CONFLICT).message("Username is being in use !").token("").build();
        } else if (email.isPresent()) {
            return AuthenticationResponse.builder().status(HttpStatus.CONFLICT).message("Email is being in use !").token("").build();
        }
        var user= User.builder().firstname(request.getFirstname()).lastname(request.getLastname()).username(request.getUsername()).email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())).role(request.getRole()).build();
        repository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).status(HttpStatus.OK).message("Registered Successfully !").build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        var user= repository.findUserByUsername(request.getUsername()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).status(HttpStatus.OK).message("Success !").build();
    }
}
