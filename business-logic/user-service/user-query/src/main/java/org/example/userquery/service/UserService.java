package org.example.userquery.service;

import org.example.sharedlibrary.response.WrapperResponse;
import org.example.userdomain.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    boolean exitByUsername(String username);

    Optional<UserEntity> findUserByUsernameAndSoftDeleteIsFalse(String username);

    WrapperResponse findAll();

    WrapperResponse findOne(String userId);
}
