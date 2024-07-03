package org.example.usercommand.service;

import org.example.sharedlibrary.response.WrapperResponse;
import org.example.userdomain.command.CreateUserCommand;
import org.example.userdomain.command.DeleteUserCommand;
import org.example.userdomain.command.UpdateUserCommand;

public interface UserService {
    WrapperResponse create(CreateUserCommand createUserCommand, String username);

    WrapperResponse update(UpdateUserCommand updateUserCommand, String username);

    WrapperResponse delete(DeleteUserCommand deleteUserCommand, String username);
}
