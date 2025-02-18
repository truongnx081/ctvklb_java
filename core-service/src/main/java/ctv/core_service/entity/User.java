package ctv.core_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Account Id", example = "1")
    Long id;

    @Schema(description = "Username", example = "user123")
    @Column(name = "user_name", unique = true, nullable = false, length = 50)
    String userName;

    @Schema(description = "Last name", example = "Nguyen")
    @Column(name = "last_name", length = 50)
    String lastName;

    @Schema(description = "First name", example = "Truong")
    @Column(name = "first_name", length = 50)
    String firstName;

    @Schema(description = "Email", example = "truong@gmail.com")
    @Column(name = "email", unique = true, length = 100)
    String email;

    @Schema(description = "Password")
    @Column(name = "password", nullable = false)
    String password;

    @Schema(description = "Date created", example = "2025-02-01")
    @CreatedDate
    @Column(name = "date_created", nullable = false, updatable = false)
    LocalDateTime dateCreated;

    @Schema(description = "Last modified date", example = "2025-02-01")
    @LastModifiedDate
    @Column(name = "date_updated")
    LocalDateTime dateUpdated;

    @Schema(description = "Created by", defaultValue = "TruongNX")
    @CreatedBy
    @Column(name = "created_by")
    String createdBy;

    @Schema(description = "Last modified by", defaultValue = "TruongNX")
    @LastModifiedBy
    @Column(name = "last_modified_by")
    String lastModifiedBy;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Enrollment> enrollments;


    @Schema(description = "User role", example = "ADMIN")
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
