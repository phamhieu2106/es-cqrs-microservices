package org.example.customercommand.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.customercommand.client.CustomerQueryClient;
import org.example.customercommand.service.CustomerHandlerService;
import org.example.customercommand.service.EventProducerService;
import org.example.customercommand.service.EventStoreService;
import org.example.customerdomain.aggregate.CustomerAggregate;
import org.example.customerdomain.command.CreateCustomerCommand;
import org.example.customerdomain.command.DeleteCustomerCommand;
import org.example.customerdomain.command.UpdateCustomerCommand;
import org.example.customerdomain.event.CreateCustomerEvent;
import org.example.customerdomain.event.DeleteCustomerEvent;
import org.example.customerdomain.event.UpdateCustomerEvent;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerHandlerServiceImpl implements CustomerHandlerService {

    private final String TOPIC = "customer_service";
    private final EventStoreService eventStoreService;
    private final EventProducerService producerService;
    private final CustomerQueryClient queryClient;

    @Override
    public void handleCreate(CreateCustomerCommand command) {
        CustomerAggregate customerAggregate = new CustomerAggregate();
        CreateCustomerEvent event = customerAggregate.applyCreate(command);
        customerAggregate.setCustomerCode(generateCustomerCode());
        event.setCustomerCode(customerAggregate.getCustomerCode());
        eventStoreService.storeEvent(customerAggregate, event);
        producerService.publish(TOPIC, event);
    }

    @Override
    public void handleUpdate(UpdateCustomerCommand command) {
        CustomerAggregate customerAggregate;
        customerAggregate = eventStoreService.getUserAggregate(command.getCustomerId());
        customerAggregate.setCustomerName(command.getCustomerName());
        customerAggregate.setGender(command.getGender());
        customerAggregate.setPhoneNumber(command.getPhoneNumber());
        customerAggregate.setDateOfBirth(command.getDateOfBirth());
        customerAggregate.setAddressModels(command.getAddressModels());
        customerAggregate.setJobName(command.getJobName());
        customerAggregate.setEmail(command.getEmail());
        customerAggregate.setStatusCustomer(command.getStatusCustomer());
        customerAggregate.setProof(command.getProof());
        UpdateCustomerEvent event = customerAggregate.applyUpdate(command);
        eventStoreService.storeEvent(customerAggregate, event);
        producerService.publish(TOPIC, event);
    }

    @Override
    public void handleDelete(DeleteCustomerCommand command) {
        CustomerAggregate customerAggregate = eventStoreService.getUserAggregate(command.getCustomerId());
        DeleteCustomerEvent event = customerAggregate.applyDelete(command);
        eventStoreService.storeEvent(customerAggregate, event);
        producerService.publish(TOPIC, event);
    }

    private String generateCustomerCode() {
        String code;
        long count = queryClient.count();
        do {
            if (count == 0) {
                code = String.format("C%03d", 1);
            } else {
                code = String.format("C%03d", ++count);
            }

            if (queryClient.existsByCustomerCode(code)) {
                code = String.format("C%03d", ++count);
            }
        } while (queryClient.existsByCustomerCode(code));
        return code;
    }
}
