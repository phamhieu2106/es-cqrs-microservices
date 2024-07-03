package org.example.authserver.client;

import org.example.userdomain.command.CreateUserCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-command", path = "/internal/api/users")
public interface UserCommandClient {

    @PostMapping("/create")
    boolean create(@RequestBody CreateUserCommand command);

}
