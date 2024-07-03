package org.example.userdomain.aggregate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.sharedlibrary.base.BaseAggregate;
import org.example.sharedlibrary.base.BaseAggregateApply;
import org.example.sharedlibrary.base.BaseEvent;
import org.example.sharedlibrary.constant.GenerateConstant;
import org.example.sharedlibrary.enumerate.UserRole;
import org.example.userdomain.command.CreateUserCommand;
import org.example.userdomain.command.DeleteUserCommand;
import org.example.userdomain.command.UpdateUserCommand;
import org.example.userdomain.event.CreateUserEvent;
import org.example.userdomain.event.DeleteUserEvent;
import org.example.userdomain.event.UpdateUserEvent;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAggregate extends BaseAggregate implements BaseAggregateApply<CreateUserCommand, UpdateUserCommand, DeleteUserCommand> {
    String userId;
    String username;
    String password;
    UserRole userRole;
    Boolean isDelete = false;
    String createdBy;

    @Override
    @SuppressWarnings("unchecked")
    public <E extends BaseEvent> E applyCreate(CreateUserCommand command) {

        this.userId = GenerateConstant.generateId();
        this.username = command.getUsername();
        this.password = command.getPassword();
        this.userRole = UserRole.NHAN_VIEN;
        this.createdBy = command.getCreatedBy();

        return (E) new CreateUserEvent(
                new Date(),
                this.userId,
                this.username,
                this.password,
                this.userRole,
                false,
                this.createdBy
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends BaseEvent> E applyUpdate(UpdateUserCommand command) {

        this.userId = command.getUserId();
        this.password = command.getPassword();
        this.userRole = command.getUserRole();
        this.createdBy = command.getCreatedBy();

        return (E) new UpdateUserEvent(
                new Date(),
                false,
                this.userRole,
                this.password,
                this.userId,
                this.createdBy
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends BaseEvent> E applyDelete(DeleteUserCommand command) {
        this.userId = command.getUserId();
        this.createdBy = command.getCreatedBy();

        return (E) new DeleteUserEvent(
                new Date(),
                this.userId,
                this.createdBy
        );
    }


}
