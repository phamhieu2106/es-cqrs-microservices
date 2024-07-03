package org.example.userdomain.event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.sharedlibrary.base.BaseEvent;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteUserEvent extends BaseEvent {
    String userId;
    Boolean isDelete = true;

    public DeleteUserEvent(Date timestamp, String userId, String createdBy) {
        super(timestamp, createdBy);
        this.userId = userId;
    }
}
