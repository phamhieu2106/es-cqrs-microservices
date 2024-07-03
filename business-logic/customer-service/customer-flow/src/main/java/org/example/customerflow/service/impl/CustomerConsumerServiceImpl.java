package org.example.customerflow.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.customerdomain.entity.CustomerEntity;
import org.example.customerdomain.event.CreateCustomerEvent;
import org.example.customerdomain.event.DeleteCustomerEvent;
import org.example.customerdomain.event.UpdateCustomerEvent;
import org.example.customerflow.repository.CustomerRepository;
import org.example.customerflow.service.CustomerConsumerService;
import org.example.customerflow.service.CustomerDocService;
import org.example.sharedlibrary.base.BaseEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerConsumerServiceImpl implements CustomerConsumerService {

    private final CustomerRepository repository;
    private final CustomerDocService docService;

    @Override
    @KafkaListener(topics = "customer_service", groupId = "customer_group")
    public void handleEvent(BaseEvent event) {
        if (event instanceof CreateCustomerEvent) {
            handleCreateEvent((CreateCustomerEvent) event);
        } else if (event instanceof UpdateCustomerEvent) {
            handleUpdateEvent((UpdateCustomerEvent) event);
        } else if (event instanceof DeleteCustomerEvent) {
            handleDeleteEvent((DeleteCustomerEvent) event);
        }
    }

    @Override
    public void handleCreateEvent(CreateCustomerEvent event) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(event.getCustomerId());
        customerEntity.setCustomerCode(event.getCustomerCode());
        customerEntity.setCustomerName(event.getCustomerName());
        customerEntity.setGender(event.getGender());
        customerEntity.setEmail(event.getEmail());
        customerEntity.setPhoneNumber(event.getPhoneNumber());
        customerEntity.setDateOfBirth(event.getDateOfBirth());
        customerEntity.setAddressModels(event.getAddressModels());
        customerEntity.setProof(event.getProof());
        customerEntity.setJobName(event.getJobName());
        customerEntity.setStatusCustomer(event.getStatusCustomer());
        customerEntity.setCreatedAt(new Date());
        customerEntity.setCreatedBy(event.getCreatedBy());
        try {
            docService.bulkDoc(customerEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        repository.save(customerEntity);
    }

    @Override
    public void handleUpdateEvent(UpdateCustomerEvent event) {
        Optional<CustomerEntity> optional = repository.findById(event.getCustomerId());
        if (optional.isEmpty()) throw new EntityNotFoundException("Not Found!");
        CustomerEntity customerEntity = optional.get();
        customerEntity.setId(event.getCustomerId());
        customerEntity.setCustomerName(event.getCustomerName());
        customerEntity.setGender(event.getGender());
        customerEntity.setEmail(event.getEmail());
        customerEntity.setPhoneNumber(event.getPhoneNumber());
        customerEntity.setDateOfBirth(event.getDateOfBirth());
        customerEntity.setAddressModels(event.getAddressModels());
        customerEntity.setJobName(event.getJobName());
        customerEntity.setStatusCustomer(event.getStatusCustomer());
        repository.save(customerEntity);
    }

    @Override
    public void handleDeleteEvent(DeleteCustomerEvent event) {
        Optional<CustomerEntity> optional = repository.findById(event.getCustomerId());
        if (optional.isEmpty()) throw new EntityNotFoundException("Not Found!");
        CustomerEntity customerEntity = optional.get();
        customerEntity.setSoftDelete(true);
        repository.save(customerEntity);
    }

}
