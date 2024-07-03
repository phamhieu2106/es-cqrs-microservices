package org.example.contractcommand.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.contractcommand.repository.EventStoreRepository;
import org.example.contractcommand.service.EventStoreService;
import org.example.contractdomain.aggregate.ContractAggregate;
import org.example.contractdomain.entity.EventStoreEntity;
import org.example.sharedlibrary.base.BaseEvent;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventStoreServiceImpl implements EventStoreService {

    private final EventStoreRepository eventStoreRepository;

    @Override
    public void storeEvent(ContractAggregate aggregate, BaseEvent event) {
        EventStoreEntity eventEntity = new EventStoreEntity();
        eventEntity.setAggregateId(aggregate.getContractId());
        eventEntity.setAggregateType(event.getClass().getSimpleName());
        eventEntity.setAggregateData(aggregate);
        eventEntity.setVersion(getEventVersion(aggregate.getCustomerId()));
        eventEntity.setTimeStamp(new Date());
        eventEntity.setCreatedBy(aggregate.getCreatedBy());
        eventStoreRepository.save(eventEntity);
    }

    @Override
    public ContractAggregate getUserAggregate(String id) {
        Optional<EventStoreEntity> optional = eventStoreRepository.findFirstByAggregateIdOrderByTimeStampDesc(id);
        return optional.map(EventStoreEntity::getAggregateData).orElse(null);
    }

    private long getEventVersion(String aggregateId) {
        if (eventStoreRepository.countByAggregateId(aggregateId) == 0) {
            return 1;
        }
        return eventStoreRepository.countByAggregateId(aggregateId) + 1;
    }
}
