package ctv.core_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String courseName;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private Long adminId;
}
