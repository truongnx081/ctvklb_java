package ctv.core_service.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import ctv.core_service.dto.request.UserCreationRequest;
import ctv.core_service.dto.request.UserUpdationRequest;
import ctv.core_service.dto.response.UserResponse;

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
