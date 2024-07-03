package org.example.usercommand.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sharedlibrary.base.BaseEvent;
import org.example.usercommand.repository.EventStoreRepository;
import org.example.usercommand.service.EventStoreService;
import org.example.userdomain.aggregate.UserAggregate;
import org.example.userdomain.entity.EventStoreEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventStoreServiceImpl implements EventStoreService {

    private final EventStoreRepository eventStoreRepository;

    @Override
    public void storeEvent(UserAggregate aggregate, BaseEvent event) {
        EventStoreEntity eventEntity = new EventStoreEntity();
        eventEntity.setAggregateId(aggregate.getUserId());
        eventEntity.setAggregateType(event.getClass().getSimpleName());
        eventEntity.setAggregateData(aggregate);
        eventEntity.setVersion(getEventVersion(aggregate.getUserId()));
        eventEntity.setTimeStamp(new Date());
        event.setCreatedBy(aggregate.getCreatedBy());
        eventStoreRepository.save(eventEntity);
    }

    @Override
    public UserAggregate getUserAggregate(String id) {
        Optional<EventStoreEntity> optional = eventStoreRepository.findFirstByAggregateIdOrderByTimeStampDesc(id);
        if (optional.isEmpty()) {
            return null;
        }
        return optional.map(EventStoreEntity::getAggregateData).orElse(null);
    }

    private long getEventVersion(String aggregateId) {
        if (eventStoreRepository.countByAggregateId(aggregateId) == 0) {
            return 1;
        }
        return eventStoreRepository.countByAggregateId(aggregateId) + 1;
    }
}
