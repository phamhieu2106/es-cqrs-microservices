package org.example.contractcommand.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.contractcommand.service.EventProducerService;
import org.example.sharedlibrary.base.BaseEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventProducerServiceImpl implements EventProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publish(String topic, BaseEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
