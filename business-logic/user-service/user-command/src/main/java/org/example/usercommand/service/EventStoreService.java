package org.example.usercommand.service;

import org.example.sharedlibrary.base.BaseEvent;
import org.example.userdomain.aggregate.UserAggregate;

public interface EventStoreService {
    void storeEvent(UserAggregate aggregate, BaseEvent event);

    UserAggregate getUserAggregate(String id);


}
