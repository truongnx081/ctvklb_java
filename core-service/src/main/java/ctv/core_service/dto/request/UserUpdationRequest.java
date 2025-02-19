package ctv.core_service.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import ctv.core_service.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdationRequest implements Serializable {

    String lastName;

    String firstName;

    @Size(min = 6, message = "{message.user.password.invalid}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{6,}$", message = "{message.user.password.format}")
    String password;

    @NotBlank(message = "{message.user.email.required}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "{message.user.email.invalid}")
    String email;

    String lastModifiedBy;

    LocalDateTime dateUpdated;

    Role role;
}
