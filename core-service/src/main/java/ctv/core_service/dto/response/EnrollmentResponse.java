package ctv.core_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponse {
    private Long id;
    private Long userId;
    private Long courseId;
    private LocalDateTime enrollmentDate;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
