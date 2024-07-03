package org.example.authserver.client;


import org.example.authserver.domain.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "user-query", path = "/internal/api/users")
public interface UserQueryClient {

    @GetMapping("/exitsByUsername/{username}")
    boolean existsByUsername(@PathVariable String username);

    @GetMapping("/findUserByUsername/{username}")
    Optional<UserEntity> findUserByUsernameAndSoftDeleteIsFalse(@PathVariable String username);

}
