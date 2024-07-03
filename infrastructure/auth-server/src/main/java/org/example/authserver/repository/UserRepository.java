package org.example.authserver.repository;

import org.example.authserver.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByUsername(String username);

    boolean existsByUserCode(String userCode);

    Optional<UserEntity> findUserByUsernameAndSoftDeleteIsFalse(String username);
}
