package com.example.keycloak.mapper;


import com.example.keycloak.dto.request.UserCreationRequest;
import com.example.keycloak.dto.request.UserUpdationRequest;
import com.example.keycloak.dto.response.UserResponse;
import com.example.keycloak.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdationRequest request);
}
