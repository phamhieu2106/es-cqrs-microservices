package org.example.userdomain.repository;

import org.example.sharedlibrary.UserModel;
import org.example.userdomain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserModel findByUsername(String username);
}
