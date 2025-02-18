package ctv.core_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND("message.user.found", HttpStatus.NOT_FOUND),
    USER_EXISTED("message.user.existed", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED("message.email.existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID("message.user.username.invalid", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED("message.user.unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED( "message.user.unauthorized", HttpStatus.FORBIDDEN);
    private final String message;
    private final HttpStatusCode httpStatusCode;
}