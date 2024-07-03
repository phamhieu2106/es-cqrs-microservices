package org.example.customerquery.controller.internal;

import lombok.RequiredArgsConstructor;
import org.example.customerquery.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/api/customers")
public class CustomerQueryInternalController {

    private final CustomerService customerService;

    @GetMapping("/existsByCustomerCode/{customerCode}")
    public boolean existsByCustomerCode(@PathVariable String customerCode) {
        return customerService.existsByCustomerCode(customerCode);
    }

    @GetMapping("/count")
    public long count() {
        return customerService.count();
    }

    @GetMapping("/existsByPhoneNumber/{phoneNumber}")
    boolean existsByPhoneNumber(@PathVariable String phoneNumber) {
        return customerService.existsByPhoneNumber(phoneNumber);
    }


    @GetMapping("/existsByEmail/{email}")
    boolean existsByEmail(@PathVariable String email) {
        return customerService.existsByEmail(email);
    }


    @GetMapping("/existsByPhoneNumberAndIdIsNot/{phoneNumber}/{customerId}")
    boolean existsByPhoneNumberAndIdIsNot(@PathVariable String customerId, @PathVariable String phoneNumber) {
        return customerService.existsByPhoneNumberAndIdIsNot(customerId, phoneNumber);
    }


    @GetMapping("/existsByEmailAndIdIsNot/{email}/{customerId}")
    boolean existsByEmailAndIdIsNot(@PathVariable String email, @PathVariable String customerId) {
        return customerService.existsByEmailAndIdIsNot(customerId, email);
    }

}
