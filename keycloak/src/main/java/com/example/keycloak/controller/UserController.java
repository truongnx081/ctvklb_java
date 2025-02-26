package com.example.keycloak.controller;

import com.example.keycloak.dto.request.UserCreationRequest;
import com.example.keycloak.dto.request.UserUpdationRequest;
import com.example.keycloak.dto.response.ApiResponseWrapper;
import com.example.keycloak.dto.response.UserResponse;
import com.example.keycloak.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final MessageSource messageSource;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<ApiResponseWrapper<List<UserResponse>>> getAllUser() {
        ApiResponseWrapper<List<UserResponse>> response = new ApiResponseWrapper<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("message.get.all.user.success", null, LocaleContextHolder.getLocale()),
                userService.getAllUser());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<ApiResponseWrapper<UserResponse>> createUser(
            @RequestBody @Valid UserCreationRequest request) {
        ApiResponseWrapper<UserResponse> response = new ApiResponseWrapper<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("message.create.user.success", null, LocaleContextHolder.getLocale()),
                userService.createUser(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponseWrapper<UserResponse>> updateUser(
            @PathVariable Long userId, @RequestBody @Valid UserUpdationRequest request) {
        ApiResponseWrapper<UserResponse> response = new ApiResponseWrapper<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("message.update.user.success", null, LocaleContextHolder.getLocale()),
                userService.updateUser(userId, request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseWrapper<UserResponse>> getUserById(
            @PathVariable Long userId) {
        ApiResponseWrapper<UserResponse> response = new ApiResponseWrapper<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("message.get.user.success", null, LocaleContextHolder.getLocale()),
                userService.getUserById(userId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseWrapper<String>> deleteUserById(
            @PathVariable Long userId) {
        userService.deleteUserById(userId);
        ApiResponseWrapper<String> response = new ApiResponseWrapper<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("message.delete.user.success", null, LocaleContextHolder.getLocale()),
                null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my-infor")
    public ResponseEntity<ApiResponseWrapper<UserResponse>> getMyInfor() {
        ApiResponseWrapper<UserResponse> response = new ApiResponseWrapper<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("message.user.get.information", null, LocaleContextHolder.getLocale()),
                userService.getMyInfor());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
