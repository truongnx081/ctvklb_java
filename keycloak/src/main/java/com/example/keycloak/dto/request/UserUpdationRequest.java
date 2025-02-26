package com.example.keycloak.dto.request;


import com.example.keycloak.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

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
