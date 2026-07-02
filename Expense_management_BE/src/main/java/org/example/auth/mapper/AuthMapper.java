package org.example.auth.mapper;

import org.example.auth.dto.response.ClientResponse;
import org.example.auth.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    ClientResponse toUserResponse(Client user);
}
