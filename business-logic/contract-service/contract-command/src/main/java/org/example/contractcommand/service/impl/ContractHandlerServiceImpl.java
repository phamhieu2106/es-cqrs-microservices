package org.example.contractcommand.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.contractcommand.client.CustomerQueryClient;
import org.example.contractcommand.client.InsuranceQueryClient;
import org.example.contractcommand.service.ContractHandlerService;
import org.example.contractcommand.service.EventProducerService;
import org.example.contractcommand.service.EventStoreService;
import org.example.contractdomain.aggregate.ContractAggregate;
import org.example.contractdomain.command.CreateContractCommand;
import org.example.contractdomain.command.DeleteContractCommand;
import org.example.contractdomain.command.UpdateContractCommand;
import org.example.contractdomain.event.CreateContractEvent;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractHandlerServiceImpl implements ContractHandlerService {

    private final String TOPIC = "customer_service";
    private final EventStoreService eventStoreService;
    private final EventProducerService producerService;
    private final CustomerQueryClient customerClient;
    private final InsuranceQueryClient insuranceClient;

    @Override
    public void handleCreate(CreateContractCommand command) {
        ContractAggregate aggregate = new ContractAggregate();
        CreateContractEvent event = aggregate.applyCreate(command);
        aggregate.setCustomerId(command.getCustomerId());
        eventStoreService.storeEvent(aggregate, event);
    }

    @Override
    public void handleUpdate(UpdateContractCommand command) {

    }

    @Override
    public void handleDelete(DeleteContractCommand command) {

    }

}
