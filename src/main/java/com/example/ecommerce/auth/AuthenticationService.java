package com.example.ecommerce.auth;

import com.example.ecommerce.config.JwtService;
import com.example.ecommerce.token.Token;
import com.example.ecommerce.token.TokenRepository;
import com.example.ecommerce.token.TokenType;
import com.example.ecommerce.user.Role;
import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var username = userRepository.findUserByUsername(request.getUsername());
        var email = userRepository.findUserByEmail(request.getEmail());
        if (username.isPresent()) {
            return AuthenticationResponse.builder().status(HttpStatus.CONFLICT).message("Username is being in use !").accessToken("").build();
        } else if (email.isPresent()) {
            return AuthenticationResponse.builder().status(HttpStatus.CONFLICT).message("Email is being in use !").accessToken("").build();
        }
        var user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname()).username(request.getUsername()).email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).status(HttpStatus.OK).message("Registered Successfully !").build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = userRepository.findUserByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).status(HttpStatus.OK).message("Success !").build();
    }


    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(Math.toIntExact(user.getId()));
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(
                    t -> {
                        t.setExpired(true);
                        t.setRevoked(true);
                    }
            );
            tokenRepository.saveAll(validUserTokens);
        }

    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthenticationResponse refreshToken(String refreshToken){
        // Check if refresh token is present
        if (refreshToken == null) {
            // Handle case where refresh token is missing
            // For example, return a ResponseEntity with an error response
            return AuthenticationResponse.builder()
                            .message("Refresh token not provided")
                            .status(HttpStatus.BAD_REQUEST)
                            .build();
        }


        // Extract username from refresh token
        String username = jwtService.extractUsername(refreshToken);
        if (username == null) {
            // Handle case where username cannot be extracted from refresh token
            // For example, return a ResponseEntity with an error response
            return AuthenticationResponse.builder()
                            .message("Invalid refresh token")
                            .status(HttpStatus.BAD_REQUEST)
                            .build();
        }

       // Retrieve user from repository
        User user = userRepository.findUserByUsername(username).orElse(null);
        if (user == null) {
            // Handle case where user cannot be found
            // For example, return a ResponseEntity with an error response
            return AuthenticationResponse.builder()
                            .message("User not found")
                            .status(HttpStatus.NOT_FOUND)
                            .build();
        }

        // Generate new access token
        String newAccessToken = jwtService.generateToken(user);

        // Revoke all user tokens and save new access token
        revokeAllUserTokens(user);
        saveUserToken(user, newAccessToken);

        // Build and return the authentication response object including refresh token
        AuthenticationResponse response = AuthenticationResponse.builder()
                .accessToken(newAccessToken)
                .message("Refresh token successful")
                .status(HttpStatus.OK)
                .refreshToken(refreshToken)
                .build();

        return response;
    }
}
