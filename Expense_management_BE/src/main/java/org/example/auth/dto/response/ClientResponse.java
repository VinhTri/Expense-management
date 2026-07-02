package org.example.auth.dto.response;

import org.example.auth.entity.Client;
import org.example.auth.entity.Role;

public record ClientResponse(
        int id,
        String username,
        String email,
        Role role

) {
    public static ClientResponse fromEntity(Client entity) {
        return new ClientResponse(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getRole()

        );
    }
}
