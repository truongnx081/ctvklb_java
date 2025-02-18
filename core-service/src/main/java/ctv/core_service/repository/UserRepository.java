package ctv.core_service.repository;


import ctv.core_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

    Optional<User> findByUserName(String username);
}
