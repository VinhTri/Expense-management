package org.example.auth.controller;

import jakarta.validation.Valid;
import org.example.auth.dto.request.RegisterRequest;
import org.example.auth.dto.response.ClientResponse;
import org.example.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<ClientResponse> register(@Valid @RequestBody RegisterRequest request) {
        ClientResponse response = authService.register(request);
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(response);
    }
}

