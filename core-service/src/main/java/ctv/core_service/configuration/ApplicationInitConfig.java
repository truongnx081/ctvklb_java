package ctv.core_service.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import ctv.core_service.entity.Role;
import ctv.core_service.entity.User;
import ctv.core_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin123";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin123";

    @NonFinal
    static final String USER_USER_NAME = "user123";

    @NonFinal
    static final String USER_PASSWORD = "user123";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driver-class-name",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.findByUserName(ADMIN_USER_NAME).isEmpty()) {
                User admin = User.builder()
                        .userName(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(admin);
            }
            if (userRepository.findByUserName(USER_USER_NAME).isEmpty()) {
                User user = User.builder()
                        .userName(USER_USER_NAME)
                        .password(passwordEncoder.encode(USER_PASSWORD))
                        .role(Role.USER)
                        .build();
                userRepository.save(user);
            }
        };
    }
}
