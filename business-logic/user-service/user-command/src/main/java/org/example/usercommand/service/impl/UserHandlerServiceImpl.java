package org.example.usercommand.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.usercommand.service.EventStoreService;
import org.example.usercommand.service.UserHandlerService;
import org.example.usercommand.service.UserProducerService;
import org.example.userdomain.aggregate.UserAggregate;
import org.example.userdomain.command.CreateUserCommand;
import org.example.userdomain.command.DeleteUserCommand;
import org.example.userdomain.command.UpdateUserCommand;
import org.example.userdomain.event.CreateUserEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandlerServiceImpl implements UserHandlerService {
    private final String TOPIC = "user_service";
    private final EventStoreService eventStoreService;
    private final UserProducerService producerService;

    @Override
    public void handleCreate(CreateUserCommand command) {
        UserAggregate aggregate = new UserAggregate();
        CreateUserEvent event = aggregate.applyCreate(command);
        eventStoreService.storeEvent(aggregate, event);
        producerService.publish(TOPIC, event);
    }

    @Override
    public void handleUpdate(UpdateUserCommand command) {
        UserAggregate aggregate = new UserAggregate();
        CreateUserEvent event = aggregate.applyUpdate(command);
        eventStoreService.storeEvent(aggregate, event);
        producerService.publish(TOPIC, event);
    }

    @Override
    public void handleDelete(DeleteUserCommand command) {
        UserAggregate aggregate = eventStoreService.getUserAggregate(command.getUserId());
        CreateUserEvent event = aggregate.applyDelete(command);
        eventStoreService.storeEvent(aggregate, event);
        producerService.publish(TOPIC, event);
    }
}
