package ctv.core_service.controller;

import java.util.List;

import jakarta.validation.Valid;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import ctv.core_service.dto.request.UserCreationRequest;
import ctv.core_service.dto.request.UserUpdationRequest;
import ctv.core_service.dto.response.ApiResponseWrapper;
import ctv.core_service.dto.response.UserResponse;
import ctv.core_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Tag(name = "User Management", description = "Operations related to User entity")
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final MessageSource messageSource;


    @Operation(summary = "Get all list user", description = "")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "successfully",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = UserResponse.class),
                                        examples =
                                                @ExampleObject(
                                                        value =
                                                                """
					{
						"userName": "john_doe2",
						"lastName": "Doe",
						"firstName": "John",
						"email": "john.doe@example.com",
						"password": "password123",
						"role": "ADMIN"
					}
				"""))),
                @ApiResponse(responseCode = "404", description = "Empty list ")
            })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<ApiResponseWrapper<List<UserResponse>>> getAllUser() {
        ApiResponseWrapper<List<UserResponse>> response = new ApiResponseWrapper<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("message.get.all.user.success",
                        null,
                        LocaleContextHolder.getLocale()),
                userService.getAllUser());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create user account", description = "")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "successfully",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = UserResponse.class),
                                        examples =
                                                @ExampleObject(
                                                        value =
                                                                """
						{
							"userName": "john_doe2",
							"lastName": "Doe",
							"firstName": "John",
							"email": "john.doe@example.com",
							"password": "password123",
							"role": "ADMIN"
							}
						""")))
            })
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

    @Operation(summary = "Update user by ID", description = "Retrieve user information based on the provided user ID")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = UserResponse.class),
                                        examples =
                                                @ExampleObject(
                                                        value =
                                                                """
				{
					"status": 200,
					"message": "User retrieved successfully",
					"data": {
						"userName": "john_doe",
						"lastName": "Doe",
						"firstName": "John",
						"email": "john.doe@example.com",
						"role": "ADMIN"
					}
				}
			"""))),
                @ApiResponse(responseCode = "404", description = "User not found"),
                @ApiResponse(responseCode = "500", description = "Internal server error")
            })
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

    @Operation(summary = "Get user by ID", description = "Retrieve user information based on the provided user ID")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully retrieved user",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = UserResponse.class),
                                        examples =
                                                @ExampleObject(
                                                        value =
                                                                """
				{
					"status": 200,
					"message": "User retrieved successfully",
					"data": {
						"userName": "john_doe",
						"lastName": "Doe",
						"firstName": "John",
						"email": "john.doe@example.com",
						"role": "ADMIN"
					}
				}
			"""))),
                @ApiResponse(responseCode = "404", description = "User not found"),
                @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseWrapper<UserResponse>> getUserById(
            @Parameter(description = "ID of the user to be retrieved", example = "1") @PathVariable Long userId) {
        ApiResponseWrapper<UserResponse> response = new ApiResponseWrapper<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("message.get.user.success", null, LocaleContextHolder.getLocale()),
                userService.getUserById(userId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseWrapper<String>> deleteUserById(
            @Parameter(description = "ID of the user to be deleted", example = "1") @PathVariable Long userId) {
        userService.deleteUserById(userId);
        ApiResponseWrapper<String> response = new ApiResponseWrapper<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("message.delete.user.success", null, LocaleContextHolder.getLocale()),
                null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete user by ID", description = "Retrieve user information based on the provided user ID")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = String.class),
                                        examples =
                                                @ExampleObject(
                                                        value =
                                                                """
				{
					"status": 200,
					"message": "Delete user successfully",
					"data": null
					}
				}
			"""))),
                @ApiResponse(responseCode = "404", description = "User not found"),
                @ApiResponse(responseCode = "500", description = "Internal server error")
            })
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
