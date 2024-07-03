package org.example.usercommand.controller;

import lombok.RequiredArgsConstructor;
import org.example.sharedlibrary.HeaderConstant;
import org.example.sharedlibrary.response.WrapperResponse;
import org.example.usercommand.service.UserService;
import org.example.userdomain.command.CreateUserCommand;
import org.example.userdomain.command.DeleteUserCommand;
import org.example.userdomain.command.UpdateUserCommand;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public WrapperResponse create(@RequestBody CreateUserCommand command, @RequestHeader(HeaderConstant.REQUEST_HEADER) String username) {
        return userService.create(command, username);
    }

    @PostMapping("/update/{userId}")
    public WrapperResponse update(@RequestBody UpdateUserCommand command
            , @PathVariable String userId, @RequestHeader(HeaderConstant.REQUEST_HEADER) String username) {
        command.setUserId(userId);
        return userService.update(command, username);
    }

    @PostMapping("/delete/{userId}")
    public WrapperResponse delete(@PathVariable String userId, @RequestHeader(HeaderConstant.REQUEST_HEADER) String username) {
        return userService.delete(new DeleteUserCommand(userId), username);
    }
}
