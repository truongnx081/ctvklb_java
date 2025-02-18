package ctv.core_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String courseName;
    String description;

    @CreatedDate
    @Column(name = "date_created", nullable = false, updatable = false)
    LocalDateTime dateCreated;

    @LastModifiedDate
    @Column(name = "date_updated", insertable = false)
    LocalDateTime dateUpdated;
    @Schema(description = "Created by", defaultValue = "TruongNX")

    @CreatedBy
    @Column(name = "created_by")
    String createdBy;

    @Schema(description = "Last modified by", defaultValue = "TruongNX")
    @LastModifiedBy
    @Column(name = "last_modified_by")
    String lastModifiedBy;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    List<Enrollment> enrollments;
}
