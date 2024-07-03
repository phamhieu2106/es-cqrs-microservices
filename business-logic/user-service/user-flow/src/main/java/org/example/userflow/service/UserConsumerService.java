package org.example.userflow.service;

import org.example.sharedlibrary.base.BaseConsumer;
import org.example.userdomain.event.CreateUserEvent;
import org.example.userdomain.event.DeleteUserEvent;
import org.example.userdomain.event.UpdateUserEvent;

public interface UserConsumerService extends BaseConsumer<
        CreateUserEvent, UpdateUserEvent, DeleteUserEvent> {
}
