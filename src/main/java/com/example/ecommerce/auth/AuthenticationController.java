package com.example.ecommerce.auth;

import com.example.ecommerce.item.Item;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ) {
        // Authenticate the user and get the tokens
        AuthenticationResponse authenticationResponse = service.authenticate(request);

        // Set the access token in the response body
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + authenticationResponse.getAccessToken());

        // Set the refresh token in a cookie
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", authenticationResponse.getRefreshToken())
                .httpOnly(true)
                .maxAge(Duration.ofDays(1))
                .path("/")
                .sameSite("none")
                .secure(false)
                .domain("localhost")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        return ResponseEntity.ok()
                .headers(headers)
                .body(authenticationResponse);
    }
    @GetMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse>  refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve the refresh token from the cookie
        Cookie[] cookies = request.getCookies();
        String refreshToken = null;
        System.out.println("REFRESHHHHH " +cookies);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    System.out.println("REFRESH " +refreshToken);
                    break;
                }
            }
        }

        if (refreshToken == null) {
            // Handle case where refresh token is missing
            // For example, return an error response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Now you have the refresh token, you can use it in your service
        return ResponseEntity.ok(service.refreshToken(refreshToken));
    }

    @GetMapping(path = "/testGet")
    public List<Number> testGet() {
        List<Number> numbers = Arrays.asList(1, 2, 3);
        return numbers;
    }
}
