package ctv.core_service.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
