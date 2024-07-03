package org.example.usercommand.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sharedlibrary.response.WrapperResponse;
import org.example.usercommand.service.UserHandlerService;
import org.example.usercommand.service.UserService;
import org.example.userdomain.command.CreateUserCommand;
import org.example.userdomain.command.DeleteUserCommand;
import org.example.userdomain.command.UpdateUserCommand;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserHandlerService handlerService;

    @Override
    public WrapperResponse create(CreateUserCommand createUserCommand, String username) {
        try {
            createUserCommand.setCreatedBy(username);
            handlerService.handleCreate(createUserCommand);
        } catch (Exception e) {
            return new WrapperResponse().fail(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), HttpStatus.OK
        );
    }

    @Override
    public WrapperResponse update(UpdateUserCommand updateUserCommand, String username) {
        try {
            updateUserCommand.setCreatedBy(username);
            handlerService.handleUpdate(updateUserCommand);
        } catch (Exception e) {
            return new WrapperResponse().fail(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), HttpStatus.OK
        );
    }

    @Override
    public WrapperResponse delete(DeleteUserCommand deleteUserCommand, String username) {
        try {
            deleteUserCommand.setCreatedBy(username);
            handlerService.handleDelete(deleteUserCommand);
        } catch (Exception e) {
            return new WrapperResponse().fail(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), HttpStatus.OK
        );
    }
}
