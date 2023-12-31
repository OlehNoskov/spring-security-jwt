package io.getarrays.springsecurity.repository;

import io.getarrays.springsecurity.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Since email is unique, we'll find users by email
    Optional<User> findByEmail(String email);

}
