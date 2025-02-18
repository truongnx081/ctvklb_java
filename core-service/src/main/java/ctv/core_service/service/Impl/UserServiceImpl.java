package ctv.core_service.service.Impl;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ctv.common_api.service.NotificationService;
import ctv.core_service.dto.request.UserCreationRequest;
import ctv.core_service.dto.request.UserUpdationRequest;
import ctv.core_service.dto.response.UserResponse;
import ctv.core_service.entity.Role;
import ctv.core_service.entity.User;
import ctv.core_service.exception.AppException;
import ctv.core_service.exception.ErrorCode;
import ctv.core_service.mapper.UserMapper;
import ctv.core_service.repository.UserRepository;
import ctv.core_service.service.UserService;
import event.dto.NotificationEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    KafkaTemplate<String, Object> kafkaTemplate;

    @DubboReference
    NotificationService notificationService;

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
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

        NotificationEvent notificationEvent = NotificationEvent.builder()
                .chanel("email")
                .recipient(request.getEmail())
                .subject("Welcome to My app")
                .body("Hello " + request.getUserName())
                .build();
        kafkaTemplate.send("notification-delivery", notificationEvent);

        //        kafkaTemplate.send("onboard","Kafka Welcome to "+ request.getUserName());
        //        log.info("Dubbo Welcome to: "+ notificationService.sendNotification(request.getUserName()));
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
        user.setRole(request.getRole());

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
}
