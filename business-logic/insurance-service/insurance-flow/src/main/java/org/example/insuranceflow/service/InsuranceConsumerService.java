package org.example.insuranceflow.service;

import org.example.insurancedomain.event.InsuranceCreateEvent;
import org.example.insurancedomain.event.InsuranceDeleteEvent;
import org.example.insurancedomain.event.InsuranceUpdateEvent;
import org.example.sharedlibrary.base.BaseConsumer;

public interface InsuranceConsumerService extends BaseConsumer<
        InsuranceCreateEvent, InsuranceUpdateEvent, InsuranceDeleteEvent> {
}
