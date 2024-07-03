package org.example.insurancecommand.service;

import org.example.sharedlibrary.base.BaseEvent;

public interface InsuranceProducerService {

    void publishEvent(String topic,BaseEvent event);
}
