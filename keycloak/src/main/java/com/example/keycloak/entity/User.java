package com.example.keycloak.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    Long id;


    @Column(name = "user_name", unique = true, nullable = false, length = 50)
    String userName;


    @Column(name = "last_name", length = 50)
    String lastName;


    @Column(name = "first_name", length = 50)
    String firstName;


    @Column(name = "email", unique = true, length = 100)
    String email;


    @Column(name = "password", nullable = false)
    String password;


    @CreatedDate
    @Column(name = "date_created", nullable = false, updatable = false)
    LocalDateTime dateCreated;


    @LastModifiedDate
    @Column(name = "date_updated")
    LocalDateTime dateUpdated;


    @CreatedBy
    @Column(name = "created_by")
    String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    String lastModifiedBy;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
