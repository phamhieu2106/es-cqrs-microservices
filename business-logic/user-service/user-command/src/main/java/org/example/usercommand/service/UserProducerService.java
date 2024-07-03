package org.example.usercommand.service;

import org.example.sharedlibrary.base.BaseEvent;

public interface UserProducerService {
    void publish(String topic, BaseEvent event);
}
