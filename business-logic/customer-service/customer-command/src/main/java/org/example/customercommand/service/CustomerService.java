package org.example.customercommand.service;

import org.example.customerdomain.command.CreateCustomerCommand;
import org.example.customerdomain.command.DeleteCustomerCommand;
import org.example.customerdomain.command.UpdateCustomerCommand;
import org.example.sharedlibrary.response.WrapperResponse;

public interface CustomerService {

    WrapperResponse create(CreateCustomerCommand command, String username);

    WrapperResponse update(UpdateCustomerCommand command, String username);

    WrapperResponse delete(DeleteCustomerCommand command, String username);

}
