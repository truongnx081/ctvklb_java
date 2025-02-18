package ctv.core_service.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    Long id;

    String userName;

    String lastName;

    String firstName;

    String email;

    LocalDateTime dateCreated;

    LocalDateTime dateUpdated;

    String lastModifiedBy;

    String createdBy;

    String role;
}
