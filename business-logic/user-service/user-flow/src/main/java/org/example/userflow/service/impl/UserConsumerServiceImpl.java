package org.example.userflow.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.sharedlibrary.base.BaseEvent;
import org.example.userdomain.entity.UserEntity;
import org.example.userdomain.event.CreateUserEvent;
import org.example.userdomain.event.DeleteUserEvent;
import org.example.userdomain.event.UpdateUserEvent;
import org.example.userflow.repository.UserRepository;
import org.example.userflow.service.UserConsumerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserConsumerServiceImpl implements UserConsumerService {

    private final UserRepository repository;

    @Override
    @KafkaListener(topics = "user_service", groupId = "user_group")
    public void handleEvent(BaseEvent event) {
        if (event instanceof CreateUserEvent) {
            handleCreateEvent((CreateUserEvent) event);
        } else if (event instanceof UpdateUserEvent) {
            handleUpdateEvent((UpdateUserEvent) event);
        } else if (event instanceof DeleteUserEvent) {
            handleDeleteEvent((DeleteUserEvent) event);
        }
    }

    @Override
    public void handleCreateEvent(CreateUserEvent event) {
        UserEntity entity = new UserEntity();
        entity.setId(event.getUserId());
        entity.setUserCode(UUID.randomUUID().toString());
        entity.setUsername(event.getUsername());
        entity.setPassword(event.getPassword());
        entity.setUserRole(event.getUserRole());
        entity.setRole(event.getRole());
        entity.setCreatedAt(new Date());
        repository.save(entity);
    }

    @Override
    public void handleUpdateEvent(UpdateUserEvent event) {
        Optional<UserEntity> optional = repository.findById(event.getUserId());
        if (optional.isEmpty()) throw new EntityNotFoundException("Not Found!");

        UserEntity entity = optional.get();
        entity.setPassword(event.getPassword());
        entity.setUserRole(event.getUserRole());
        repository.save(entity);
    }

    @Override
    public void handleDeleteEvent(DeleteUserEvent event) {
        Optional<UserEntity> optional = repository.findById(event.getUserId());
        if (optional.isEmpty()) throw new EntityNotFoundException("Not Found!");
        UserEntity entity = optional.get();
        entity.setSoftDelete(true);
        repository.save(entity);
    }

}
