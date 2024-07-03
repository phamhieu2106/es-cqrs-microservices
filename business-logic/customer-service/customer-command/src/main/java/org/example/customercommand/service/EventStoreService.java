package org.example.customercommand.service;

import org.example.customerdomain.aggregate.CustomerAggregate;
import org.example.sharedlibrary.base.BaseEvent;

public interface EventStoreService {

    void storeEvent(CustomerAggregate aggregate, BaseEvent event);

    CustomerAggregate getUserAggregate(String id);

}
