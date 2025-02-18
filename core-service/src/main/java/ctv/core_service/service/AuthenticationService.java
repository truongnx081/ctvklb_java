package ctv.core_service.service;

import java.text.ParseException;

import com.nimbusds.jose.JOSEException;

import ctv.core_service.dto.request.AuthenticationRequest;
import ctv.core_service.dto.request.IntrospectRequest;
import ctv.core_service.dto.request.LogoutRequest;
import ctv.core_service.dto.request.RefreshRequest;
import ctv.core_service.dto.response.AuthenticationResponse;
import ctv.core_service.dto.response.IntrospectResponse;

public interface AuthenticationService {

    IntrospectResponse introspect(IntrospectRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void logout(LogoutRequest request) throws ParseException, JOSEException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
