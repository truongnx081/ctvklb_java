package com.example.keycloak.service;



import com.example.keycloak.dto.request.UserCreationRequest;
import com.example.keycloak.dto.request.UserUpdationRequest;
import com.example.keycloak.dto.response.UserResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {

    List<UserResponse> getAllUser();

    UserResponse createUser(UserCreationRequest request);

    UserResponse updateUser(Long userId, UserUpdationRequest request);

    UserResponse getUserById(Long userId);

    void deleteUserById(Long userId);

    UserResponse getMyInfor();

    UserResponse fallbackResponseCircuitBreaker(Exception e);

    UserResponse fallbackResponseRetry(Exception e);

    UserResponse fallbackResponseRateLimiter(Exception e);

    UserResponse fallbackResponseBulkhead(Exception e);

    CompletableFuture<UserResponse> fallbackResponseTimeLimiter(Exception e);
}
