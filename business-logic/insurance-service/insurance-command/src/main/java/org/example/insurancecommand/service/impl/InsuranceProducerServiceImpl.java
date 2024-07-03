package org.example.insurancecommand.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.insurancecommand.service.InsuranceProducerService;
import org.example.sharedlibrary.base.BaseEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InsuranceProducerServiceImpl implements InsuranceProducerService {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    @Override
    public void publishEvent(String topic,BaseEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
