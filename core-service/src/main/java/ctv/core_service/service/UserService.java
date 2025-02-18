package ctv.core_service.service;

import ctv.core_service.dto.request.UserCreationRequest;
import ctv.core_service.dto.request.UserUpdationRequest;
import ctv.core_service.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUser();
    UserResponse createUser(UserCreationRequest request);

    UserResponse updateUser(Long userId, UserUpdationRequest request);

    UserResponse getUserById( Long userId);

    void deleteUser(Long userId);

    UserResponse getMyInfor();


}
