package org.example.customercommand.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customer-query", path = "/internal/api/customers")
public interface CustomerQueryClient {

    @GetMapping("/existsByCustomerCode/{customerCode}")
    boolean existsByCustomerCode(@PathVariable String customerCode);

    @GetMapping("/count")
    long count();

    @GetMapping("/existsByPhoneNumber/{phoneNumber}")
    boolean existsByPhoneNumber(@PathVariable String phoneNumber);

    @GetMapping("/existsByEmail/{email}")
    boolean existsByEmail(@PathVariable String email);

    @GetMapping("/existsByPhoneNumberAndIdIsNot/{phoneNumber}/{customerId}")
    boolean existsByPhoneNumberAndIdIsNot(@PathVariable String customerId, @PathVariable String phoneNumber);

    @GetMapping("/existsByEmailAndIdIsNot/{email}/{customerId}")
    boolean existsByEmailAndIdIsNot(@PathVariable String email, @PathVariable String customerId);

}
