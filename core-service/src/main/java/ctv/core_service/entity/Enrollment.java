package ctv.core_service.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    LocalDateTime enrollmentDate;
    @CreatedDate
    @Column(name = "date_created", nullable = false, updatable = false)
    LocalDateTime dateCreated;

    @LastModifiedDate
    @Column(name = "date_updated", insertable = false)
    LocalDateTime dateUpdated;
}
