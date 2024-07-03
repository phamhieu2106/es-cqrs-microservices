package org.example.usercommand.service;

import org.example.sharedlibrary.BaseEventHandler;
import org.example.userdomain.command.CreateUserCommand;
import org.example.userdomain.command.DeleteUserCommand;
import org.example.userdomain.command.UpdateUserCommand;

public interface UserHandlerService extends BaseEventHandler
        <CreateUserCommand, UpdateUserCommand, DeleteUserCommand> {
}
