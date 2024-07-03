package org.example.customercommand.service;

import org.example.customerdomain.command.CreateCustomerCommand;
import org.example.customerdomain.command.DeleteCustomerCommand;
import org.example.customerdomain.command.UpdateCustomerCommand;
import org.example.sharedlibrary.BaseEventHandler;

public interface CustomerHandlerService extends BaseEventHandler
        <CreateCustomerCommand, UpdateCustomerCommand, DeleteCustomerCommand> {
}
