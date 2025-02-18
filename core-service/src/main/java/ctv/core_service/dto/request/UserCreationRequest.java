package ctv.core_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserCreationRequest {

    Long id;

    @NotBlank(message = "{message.user.username.required}")
    @Size(min = 5, message = "{message.user.username.invalid}")
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]+$",
            message = "{message.user.username.format}")
    String userName;

    String lastName;

    String firstName;

    @NotBlank(message = "{message.user.password.required}")
    @Size(min = 6, message = "{message.user.password.invalid}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{6,}$", message = "{message.user.password.format}")
    String password;

    @NotBlank(message = "{message.user.email.required}")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "{message.user.email.invalid}"
    )
    String email;

    LocalDateTime dateCreated;

    String createdBy;

    String role;
}
