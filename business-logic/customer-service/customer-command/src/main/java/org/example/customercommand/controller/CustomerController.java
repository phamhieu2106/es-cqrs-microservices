package org.example.customercommand.controller;

import lombok.RequiredArgsConstructor;
import org.example.customercommand.service.CustomerService;
import org.example.customerdomain.command.CreateCustomerCommand;
import org.example.customerdomain.command.DeleteCustomerCommand;
import org.example.customerdomain.command.UpdateCustomerCommand;
import org.example.sharedlibrary.HeaderConstant;
import org.example.sharedlibrary.response.WrapperResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public WrapperResponse create(@RequestBody CreateCustomerCommand command,
                                  @RequestHeader(value = HeaderConstant.REQUEST_HEADER, defaultValue = "anonymous") String username) {
        return customerService.create(command, username);
    }

    @PostMapping("/update/{customerId}")
    public WrapperResponse update(@RequestBody UpdateCustomerCommand command,
                                  @RequestHeader(value = HeaderConstant.REQUEST_HEADER, defaultValue = "anonymous") String username, @PathVariable String customerId) {
        command.setCustomerId(customerId);
        return customerService.update(command, username);
    }

    @PostMapping("/delete/{customerId}")
    public WrapperResponse delete(@RequestBody DeleteCustomerCommand command,
                                  @RequestHeader(value = HeaderConstant.REQUEST_HEADER, defaultValue = "anonymous") String username, @PathVariable String customerId) {
        command.setCustomerId(customerId);
        return customerService.delete(command, username);
    }
}
