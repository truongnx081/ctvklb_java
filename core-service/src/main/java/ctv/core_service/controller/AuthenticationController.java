package ctv.core_service.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;

import ctv.core_service.dto.request.AuthenticationRequest;
import ctv.core_service.dto.request.IntrospectRequest;
import ctv.core_service.dto.request.LogoutRequest;
import ctv.core_service.dto.request.RefreshRequest;
import ctv.core_service.dto.response.ApiResponseWrapper;
import ctv.core_service.dto.response.AuthenticationResponse;
import ctv.core_service.dto.response.IntrospectResponse;
import ctv.core_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponseWrapper<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponseWrapper.<AuthenticationResponse>builder().data(result).build();
    }

    @PostMapping("/introspect")
    ApiResponseWrapper<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponseWrapper.<IntrospectResponse>builder().data(result).build();
    }

    @PostMapping("/refresh")
    ApiResponseWrapper<AuthenticationResponse> authenticate(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponseWrapper.<AuthenticationResponse>builder().data(result).build();
    }

    @PostMapping("/logout")
    ApiResponseWrapper<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponseWrapper.<Void>builder().build();
    }
}
