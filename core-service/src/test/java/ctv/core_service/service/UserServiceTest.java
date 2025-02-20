// package ctv.core_service.service;
//
// import ctv.core_service.dto.request.UserCreationRequest;
// import ctv.core_service.dto.response.UserResponse;
// import ctv.core_service.entity.Role;
// import ctv.core_service.entity.User;
// import ctv.core_service.exception.AppException;
// import ctv.core_service.exception.ErrorCode;
// import ctv.core_service.repository.UserRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
//
// import java.time.LocalDateTime;
//
// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyString;
//
// @SpringBootTest
// public class UserServiceTest {
//
//    @Autowired
//    private  UserService userService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    private UserCreationRequest request;
//    private UserResponse userResponse;
//    private User user;
//
//    @BeforeEach
//    void initData() {
//        request = UserCreationRequest.builder()
//                .userName("testUser123")
//                .lastName("Doe")
//                .firstName("John")
//                .password("Test123")
//                .email("john.doe@example.com")
//                .dateCreated(LocalDateTime.now())
//                .createdBy("admin")
//                .role("USER")
//                .build();
//        userResponse = UserResponse.builder()
//                .id(1L)
//                .userName("testUser123")
//                .lastName("Doe")
//                .firstName("John")
//                .email("john.doe@example.com")
//                .dateCreated(LocalDateTime.now())
//                .dateUpdated(LocalDateTime.now())
//                .lastModifiedBy("admin")
//                .createdBy("admin")
//                .role("USER")
//                .build();
//        user=User.builder()
//                .userName("testUser123")
//                .lastName("Doe")
//                .firstName("John")
//                .password("Test123")
//                .email("john.doe@example.com")
//                .dateCreated(LocalDateTime.now())
//                .createdBy("admin")
//                .role(Role.USER)
//                .build();
//    }
//
//    @Test
//    void createUser_validRequest_success(){
//        //Given
//        Mockito.when(userRepository.existsByUserName(anyString())).thenReturn(false);
//        Mockito.when(userRepository.existsByEmail(anyString())).thenReturn(false);
//        Mockito.when(userRepository.save(any())).thenReturn(user);
//
//        //when
//        userService.createUser(request);
//
//        //then
//        assertThat(userResponse.getId()).isEqualTo(1);
//        assertThat(userResponse.getUserName()).isEqualTo("testUser123");
//        assertThat(userResponse.getEmail()).isEqualTo("john.doe@example.com");
//    }
//
//    @Test
//    void createUser_userExisted_fail() {
//        // GIVEN
//        Mockito.when(userRepository.existsByUserName(anyString())).thenReturn(true);
//
//        // WHEN
//        var exception = assertThrows(AppException.class, () -> userService.createUser(request));
//
//        // THEN
//        assertThat(exception.getErrorCode().getHttpStatusCode().value()).isEqualTo(400);
//    }
//
//    @Test
//    void getMyInfor_ServiceUnavailable_ShouldTriggerFallback() {
//        // Given
//        Mockito.when(userRepository.findByUserName(anyString())).thenThrow(new RuntimeException("DB Error"));
//
//        // When
//        var exception = assertThrows(AppException.class, () -> userService.fallbackResponse(new RuntimeException("DB
// Error")));
//
//        // Then
//        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.SERVICE_UNAVAILABLE);
//    }
//
// }

package ctv.core_service.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import ctv.core_service.dto.request.UserCreationRequest;
import ctv.core_service.dto.response.UserResponse;
import ctv.core_service.entity.Role;
import ctv.core_service.entity.User;
import ctv.core_service.exception.AppException;
import ctv.core_service.exception.ErrorCode;
import ctv.core_service.repository.UserRepository;
import io.github.resilience4j.retry.annotation.Retry;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;

    @BeforeEach
    void initData() {
        request = UserCreationRequest.builder()
                .userName("testUser123")
                .lastName("Doe")
                .firstName("John")
                .password("Test123")
                .email("john.doe@example.com")
                .dateCreated(LocalDateTime.now())
                .createdBy("admin")
                .role("USER")
                .build();

        userResponse = UserResponse.builder()
                .id(1L)
                .userName("testUser123")
                .lastName("Doe")
                .firstName("John")
                .email("john.doe@example.com")
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .lastModifiedBy("admin")
                .createdBy("admin")
                .role("USER")
                .build();

        user = User.builder()
                .userName("testUser123")
                .lastName("Doe")
                .firstName("John")
                .password("Test123")
                .email("john.doe@example.com")
                .dateCreated(LocalDateTime.now())
                .createdBy("admin")
                .role(Role.USER)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // Given
        Mockito.when(userRepository.existsByUserName(anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(anyString())).thenReturn(false);
        Mockito.when(userRepository.save(any())).thenReturn(user);

        // When
        userService.createUser(request);

        // Then
        assertThat(userResponse.getUserName()).isEqualTo("testUser123");
        assertThat(userResponse.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    void createUser_userExisted_fail() {
        // Given
        Mockito.when(userRepository.existsByUserName(anyString())).thenReturn(true);

        // When
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));

        // Then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.USER_EXISTED);
    }

    @Test
    void createUser_emailExisted_fail() {
        // Given
        Mockito.when(userRepository.existsByEmail(anyString())).thenReturn(true);

        // When
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));

        // Then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.EMAIL_EXISTED);
    }

    @Test
    void createUser_saveUser_fail() {
        // Given
        Mockito.when(userRepository.existsByUserName(anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(anyString())).thenReturn(false);
        Mockito.when(userRepository.save(any())).thenThrow(new RuntimeException("Database error"));

        // When
        var exception = assertThrows(RuntimeException.class, () -> userService.createUser(request));

        // Then
        assertThat(exception.getMessage()).isEqualTo("Database error");
    }

    @Test
    @Retry(name = "getMyInfor", fallbackMethod = "fallbackMethod")
    void getMyInfor_Success() {
        // Given
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("testUser123");
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(userRepository.findByUserName("testUser123")).thenReturn(java.util.Optional.of(user));

        // When
        UserResponse result = userService.getMyInfor();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUserName()).isEqualTo("testUser123");
    }

    @Test
    void getMyInfor_CircuitBreaker_ShouldTriggerFallback() {
        // Given
        Mockito.when(userRepository.findByUserName(anyString())).thenThrow(new RuntimeException("DB Error"));

        // When
        var exception = assertThrows(
                AppException.class, () -> userService.fallbackResponseCircuitBreaker(new RuntimeException("DB Error")));

        // Then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.SERVICE_UNAVAILABLE);
    }

    @Test
    void getMyInfor_Retry_ShouldTriggerFallback() {
        // Given
        Mockito.when(userRepository.findByUserName(anyString())).thenThrow(new RuntimeException("Retry Error"));

        // When
        var exception = assertThrows(
                AppException.class, () -> userService.fallbackResponseRetry(new RuntimeException("Retry Error")));

        // Then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.TOO_MANY_ATTEMPTS);
    }

    @Test
    void getMyInfor_RateLimiter_ShouldTriggerFallback() {
        // Given
        Mockito.when(userRepository.findByUserName(anyString())).thenThrow(new RuntimeException("Rate Limit Exceeded"));

        // When
        var exception = assertThrows(
                AppException.class,
                () -> userService.fallbackResponseRateLimiter(new RuntimeException("Rate Limit Exceeded")));

        // Then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.RATE_LIMIT_EXCEEDED);
    }

    @Test
    void getMyInfor_Bulkhead_ShouldTriggerFallback() {
        // Given
        Mockito.when(userRepository.findByUserName(anyString())).thenThrow(new RuntimeException("System Overloaded"));

        // When
        var exception = assertThrows(
                AppException.class,
                () -> userService.fallbackResponseBulkhead(new RuntimeException("System Overloaded")));

        // Then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.SYSTEM_OVERLOADED);
    }

    @Test
    void getMyInfor_TimeLimiter_ShouldTriggerFallback() {
        // Given
        Mockito.when(userRepository.findByUserName(anyString())).thenThrow(new RuntimeException("Time Limit Exceeded"));

        // When
        var exception = assertThrows(
                AppException.class,
                () -> userService.fallbackResponseTimeLimiter(new RuntimeException("Time Limit Exceeded")));

        // Then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.SYSTEM_OVERLOADED);
    }
}
