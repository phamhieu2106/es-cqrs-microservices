package org.example.insurancecommand.handler;

import lombok.RequiredArgsConstructor;
import org.example.insurancecommand.client.InsuranceServiceClient;
import org.example.insurancecommand.entity.EventStoreEntity;
import org.example.insurancecommand.repository.EventStoreRepository;
import org.example.insurancecommand.service.InsuranceProducerService;
import org.example.insurancedomain.aggregate.InsuranceAggregate;
import org.example.insurancedomain.command.InsuranceCreateCommand;
import org.example.insurancedomain.command.InsuranceDeleteCommand;
import org.example.insurancedomain.command.InsuranceUpdateCommand;
import org.example.insurancedomain.converter.InsuranceAggregateConverter;
import org.example.insurancedomain.event.InsuranceCreateEvent;
import org.example.insurancedomain.event.InsuranceDeleteEvent;
import org.example.insurancedomain.event.InsuranceUpdateEvent;
import org.example.sharedlibrary.base.BaseEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class InsuranceCommandHandler {
    private final String TOPIC = "insurance_service";

    private final InsuranceProducerService insuranceProducerService;
    private final EventStoreRepository eventStoreRepository;
    private final InsuranceServiceClient insuranceServiceClient;

    public void handle(InsuranceCreateCommand command) {
        InsuranceAggregate aggregate = new InsuranceAggregate();
        InsuranceCreateEvent event = aggregate.apply(
                new InsuranceCreateCommand(command.getInsuranceName(),
                        command.getTotalPaymentFeeAmount(),
                        command.getTotalInsuranceTotalFeeAmount(),
                        generateInsuranceCode())
        );
        saveEventToEventStore(aggregate, event);
        insuranceProducerService.publishEvent(TOPIC, event);
    }

    public void handle(InsuranceUpdateCommand command) {
        InsuranceAggregate aggregate = new InsuranceAggregate();
        InsuranceUpdateEvent event = aggregate.apply(
                new InsuranceUpdateCommand(
                        command.getInsuranceId(),
                        command.getTotalPaymentFeeAmount(),
                        command.getTotalInsuranceTotalFeeAmount(),
                        command.getInsuranceName()
                        , command.getInsuranceCode())
        );
        saveEventToEventStore(aggregate, event);
        insuranceProducerService.publishEvent(TOPIC, event);
    }

    public void handle(InsuranceDeleteCommand command) {
        InsuranceAggregate aggregate = getAggregateById(command.getInsuranceId());
        InsuranceDeleteEvent event = aggregate.apply(
                new InsuranceDeleteCommand(
                        command.getInsuranceId()
                ));
        saveEventToEventStore(aggregate, event);
        insuranceProducerService.publishEvent(TOPIC, event);
    }

    private void saveEventToEventStore(InsuranceAggregate aggregate, BaseEvent event) {
        EventStoreEntity eventEntity = new EventStoreEntity();
        eventEntity.setAggregateId(aggregate.getInsuranceId());
        eventEntity.setAggregateType(event.getClass().getSimpleName());
        eventEntity.setAggregateData(aggregate);
        eventEntity.setVersion(getEventVersion(aggregate.getInsuranceId()));
        eventEntity.setTimeStamp(new Date());

        eventStoreRepository.insertEvent(
                UUID.randomUUID().toString(),
                eventEntity.getAggregateId(),
                eventEntity.getAggregateType(),
                new InsuranceAggregateConverter().convertToDatabaseColumn(eventEntity.getAggregateData()),
                eventEntity.getVersion(),
                eventEntity.getTimeStamp()
        );
    }

    private long getEventVersion(String aggregateId) {
        if (eventStoreRepository.countByAggregateId(aggregateId) == 0) {
            return 1;
        }
        return eventStoreRepository.countByAggregateId(aggregateId) + 1;
    }

    private String generateInsuranceCode() {
        String code;
        long count = insuranceServiceClient.getInsuranceQueryCount();
        do {
            if (count == 0) {
                code = String.format("I%03d", ++count);
            } else {
                code = String.format("I%03d", count);
            }

            if (insuranceServiceClient.isInsuranceCodeExitsByInsuranceCode(code)) {
                code = String.format("I%03d", ++count);
            }
        } while (insuranceServiceClient.isInsuranceCodeExitsByInsuranceCode(code));
        return code;
    }

    private InsuranceAggregate getAggregateById(String aggregateId) {
        Optional<EventStoreEntity> optional = eventStoreRepository.findFirstByAggregateIdOrderByTimeStampDesc(aggregateId);
        return optional.map(EventStoreEntity::getAggregateData).orElse(null);
    }


}
