package org.example.contractcommand.service;

import org.example.sharedlibrary.base.BaseEvent;

public interface EventProducerService {
    void publish(String topic, BaseEvent event);
}
