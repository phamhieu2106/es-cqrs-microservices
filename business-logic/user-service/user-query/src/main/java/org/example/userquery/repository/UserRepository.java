package org.example.userquery.repository;

import org.example.userdomain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUsername(String username);

    Optional<UserEntity> findUserByUsernameAndSoftDeleteIsFalse(String username);
}
