package org.example.userdomain.event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.sharedlibrary.base.BaseEvent;
import org.example.sharedlibrary.enumerate.UserRole;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserEvent extends BaseEvent {
    String userId;
    String password;
    UserRole userRole;
    Boolean isDelete = false;

    public UpdateUserEvent(Date timestamp, Boolean isDelete, UserRole userRole, String password, String userId, String createdBy) {
        super(timestamp, createdBy);
        this.isDelete = isDelete;
        this.userRole = userRole;
        this.password = password;
        this.userId = userId;
    }
}
