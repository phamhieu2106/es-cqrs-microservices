package org.example.userdomain.event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.sharedlibrary.base.BaseEvent;
import org.example.sharedlibrary.enumerate.Role;
import org.example.sharedlibrary.enumerate.UserRole;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserEvent extends BaseEvent {
    String userId;
    String username;
    String password;
    Role role = Role.USER;
    UserRole userRole;
    Boolean isDelete = false;

    public CreateUserEvent(Date timestamp, String userId
            , String username, String password, UserRole userRole, Boolean isDelete, String createdBy) {
        super(timestamp, createdBy);
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.isDelete = isDelete;
    }
}
