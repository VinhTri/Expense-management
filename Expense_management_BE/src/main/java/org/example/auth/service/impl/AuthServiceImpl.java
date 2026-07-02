package org.example.auth.service.impl;

import org.example.auth.dto.request.RegisterRequest;
import org.example.auth.dto.response.ClientResponse;
import org.example.auth.entity.Client;
import org.example.auth.entity.Role;
import org.example.auth.repository.ClientRepository;
import org.example.auth.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthServiceImpl implements AuthService {
    // 1. Chỉ giữ lại đúng 2 công cụ: Thủ kho (Repository) và Máy xay (Encoder)
    private final ClientRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(ClientRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 2. Giữ nguyên toàn bộ logic của hàm Đăng ký
    @Override
    public ClientResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email đã được sử dụng!");
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        Client user = new Client(
                request.username(),
                request.email(),
                encodedPassword,
                Role.ROLE_CLIENT
        );

        Client savedUser = userRepository.save(user);

        return ClientResponse.fromEntity(savedUser);
    }

}
