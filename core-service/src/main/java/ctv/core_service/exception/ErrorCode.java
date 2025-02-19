package ctv.core_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION("Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND("message.user.found", HttpStatus.NOT_FOUND),
    USER_EXISTED("message.user.existed", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED("message.email.existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID("message.user.username.invalid", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED("message.user.unauthenticated", HttpStatus.UNAUTHORIZED),
    SERVICE_UNAVAILABLE("message.server.unavailable", HttpStatus.SERVICE_UNAVAILABLE),
    UNAUTHORIZED("message.user.unauthorized", HttpStatus.FORBIDDEN),
    TOO_MANY_ATTEMPTS("message.server.too_many_attempts", HttpStatus.TOO_MANY_REQUESTS),
    RATE_LIMIT_EXCEEDED("message.server.rate_limit_exceeded", HttpStatus.TOO_MANY_REQUESTS),
    SYSTEM_OVERLOADED("message.server.system_overloaded", HttpStatus.SERVICE_UNAVAILABLE);

    private final String message;
    private final HttpStatusCode httpStatusCode;
}
