package ctv.core_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ctv.core_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    Optional<User> findByUserName(String username);
}
