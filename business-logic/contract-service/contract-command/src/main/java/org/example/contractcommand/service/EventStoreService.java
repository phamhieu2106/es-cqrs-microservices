package org.example.contractcommand.service;

import org.example.contractdomain.aggregate.ContractAggregate;
import org.example.sharedlibrary.base.BaseEvent;

public interface EventStoreService {

    void storeEvent(ContractAggregate aggregate, BaseEvent event);

    ContractAggregate getUserAggregate(String id);

}
