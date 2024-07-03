package org.example.usercommand.controller.internal;

import lombok.RequiredArgsConstructor;
import org.example.sharedlibrary.HeaderConstant;
import org.example.usercommand.service.UserService;
import org.example.userdomain.command.CreateUserCommand;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/api/users")
public class UserInternalController {

    private final UserService userService;

    @PostMapping("/create")
    public boolean create(@RequestBody CreateUserCommand command, @RequestHeader(HeaderConstant.REQUEST_HEADER) String username) {
        return userService.create(command, username).isSuccessful();
    }

}
