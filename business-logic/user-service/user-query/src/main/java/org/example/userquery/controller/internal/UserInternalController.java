package org.example.userquery.controller.internal;

import lombok.RequiredArgsConstructor;
import org.example.userdomain.entity.UserEntity;
import org.example.userquery.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/internal/api/users")
@RequiredArgsConstructor
public class UserInternalController {

    private final UserService userService;

    @GetMapping("/exitsByUsername/{username}")
    boolean existsByUsername(@PathVariable String username) {
        return userService.exitByUsername(username);
    }

    @GetMapping("/findUserByUsername/{username}")
    Optional<UserEntity> findUserByUsernameAndSoftDeleteIsFalse(@PathVariable String username) {
        return userService.findUserByUsernameAndSoftDeleteIsFalse(username);
    }
}
