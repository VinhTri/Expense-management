package org.example.auth.service;

import org.example.auth.dto.request.RegisterRequest;
import org.example.auth.dto.response.ClientResponse;

public interface AuthService {
    ClientResponse register(RegisterRequest request);
}
