package org.example.userquery.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sharedlibrary.response.WrapperResponse;
import org.example.userdomain.entity.UserEntity;
import org.example.userquery.repository.UserRepository;
import org.example.userquery.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public boolean exitByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<UserEntity> findUserByUsernameAndSoftDeleteIsFalse(String username) {
        return userRepository.findUserByUsernameAndSoftDeleteIsFalse(username);
    }

    @Override
    public WrapperResponse findAll() {
        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), userRepository.findAll()
        );
    }

    @Override
    public WrapperResponse findOne(String userId) {
        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), userRepository.findById(userId).orElse(null)
        );
    }
}
