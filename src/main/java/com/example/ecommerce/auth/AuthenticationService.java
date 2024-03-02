package com.example.ecommerce.auth;

import com.example.ecommerce.config.JwtService;
import com.example.ecommerce.token.Token;
import com.example.ecommerce.token.TokenRepository;
import com.example.ecommerce.token.TokenType;
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
                .password(passwordEncoder.encode(request.getPassword())).role(request.getRole()).build();
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
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
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

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;

        if(authHeader==null || !authHeader.startsWith("Bearer "))
        {
            return;
        }
        refreshToken=authHeader.substring(7);
        username=jwtService.extractUsername(refreshToken);
        if(username!=null)
        {
            var user = this.userRepository.findUserByUsername(username).orElseThrow();
            if(jwtService.isTokenValid(refreshToken,user))
            {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user,accessToken);
                var authResponse= AuthenticationResponse.builder().accessToken(accessToken).message("refresh token").status(HttpStatus.OK).refreshToken(refreshToken).build();
                new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
            }
        }
    }
}
