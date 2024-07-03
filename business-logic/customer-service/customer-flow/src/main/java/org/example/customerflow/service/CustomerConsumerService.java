package org.example.customerflow.service;

import org.example.customerdomain.event.CreateCustomerEvent;
import org.example.customerdomain.event.DeleteCustomerEvent;
import org.example.customerdomain.event.UpdateCustomerEvent;
import org.example.sharedlibrary.base.BaseConsumer;

public interface CustomerConsumerService extends BaseConsumer<
        CreateCustomerEvent, UpdateCustomerEvent, DeleteCustomerEvent> {

}
