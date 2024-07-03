package org.example.insuranceflow.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.insurancedomain.event.InsuranceCreateEvent;
import org.example.insurancedomain.event.InsuranceDeleteEvent;
import org.example.insurancedomain.event.InsuranceUpdateEvent;
import org.example.insurancedomain.entity.InsuranceEntity;
import org.example.insuranceflow.repository.InsuranceEntityRepository;
import org.example.insuranceflow.service.InsuranceConsumerService;
import org.example.sharedlibrary.base.BaseEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class InsuranceConsumerServiceImpl implements InsuranceConsumerService {

    private final InsuranceEntityRepository repository;

    @Override
    @KafkaListener(topics = "insurance_service", groupId = "insurance_group")
    public void handleEvent(BaseEvent event) {
        if (event instanceof InsuranceCreateEvent) {
            handleCreateEvent((InsuranceCreateEvent) event);
        } else if (event instanceof InsuranceUpdateEvent) {
            handleUpdateEvent((InsuranceUpdateEvent) event);
        } else if (event instanceof InsuranceDeleteEvent) {
            handleDeleteEvent((InsuranceDeleteEvent) event);
        }
    }

    @Override
    public void handleCreateEvent(InsuranceCreateEvent event) {
        InsuranceEntity entity = new InsuranceEntity();
        entity.setId(event.getInsuranceId());
        entity.setInsuranceCode(event.getInsuranceCode());
        entity.setInsuranceName(event.getInsuranceName());
        entity.setTotalPaymentFeeAmount(event.getTotalPaymentFeeAmount());
        entity.setTotalInsuranceTotalFeeAmount(event.getTotalInsuranceTotalFeeAmount());
        repository.save(entity);
    }

    @Override
    public void handleUpdateEvent(InsuranceUpdateEvent event) {
        Optional<InsuranceEntity> optional = repository.findById(event.getInsuranceId());
        if(optional.isEmpty()) throw new EntityNotFoundException("Not Found Insurance!");

        InsuranceEntity entity = optional.get();
        entity.setInsuranceCode(event.getInsuranceCode());
        entity.setInsuranceName(event.getInsuranceName());
        entity.setTotalPaymentFeeAmount(entity.getTotalPaymentFeeAmount());
        entity.setTotalInsuranceTotalFeeAmount(entity.getTotalInsuranceTotalFeeAmount());
        repository.save(entity);
    }

    @Override
    public void handleDeleteEvent(InsuranceDeleteEvent event) {
        Optional<InsuranceEntity> optional = repository.findById(event.getInsuranceId());
        if(optional.isEmpty()) throw new EntityNotFoundException("Not Found Insurance!");
        InsuranceEntity entity = optional.get();
        entity.setIsDelete(true);
        repository.save(entity);
    }

}
