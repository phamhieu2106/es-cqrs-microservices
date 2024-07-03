package org.example.usercommand.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sharedlibrary.base.BaseEvent;
import org.example.usercommand.service.UserProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProducerServiceImpl implements UserProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publish(String topic, BaseEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
