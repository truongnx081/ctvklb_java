package com.example.keycloak.service.Impl;

import com.example.keycloak.entity.Role;
import com.example.keycloak.service.UserService;
import com.example.keycloak.dto.request.UserCreationRequest;
import com.example.keycloak.dto.request.UserUpdationRequest;
import com.example.keycloak.dto.response.UserResponse;
import com.example.keycloak.entity.User;
import com.example.keycloak.exception.AppException;
import com.example.keycloak.exception.ErrorCode;
import com.example.keycloak.mapper.UserMapper;
import com.example.keycloak.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    MessageSource messageSource;

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            log.error("User existed: {}", request.getUserName());
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = Role.valueOf("USER");
        user.setRole(role);
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(Long userId, UserUpdationRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }


    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }


    @Override
    public UserResponse getMyInfor() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUserName(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse fallbackResponseCircuitBreaker(Exception e) {
        throw new AppException(ErrorCode.SERVICE_UNAVAILABLE);
    }

    @Override
    public UserResponse fallbackResponseRetry(Exception e) {
        throw new AppException(ErrorCode.TOO_MANY_ATTEMPTS);
    }

    @Override
    public UserResponse fallbackResponseRateLimiter(Exception e) {
        throw new AppException(ErrorCode.RATE_LIMIT_EXCEEDED);
    }

    @Override
    public UserResponse fallbackResponseBulkhead(Exception e) {
        throw new AppException(ErrorCode.SYSTEM_OVERLOADED);
    }

    @Override
    public CompletableFuture<UserResponse> fallbackResponseTimeLimiter(Exception e) {
        throw new AppException(ErrorCode.SYSTEM_OVERLOADED);
    }
}
