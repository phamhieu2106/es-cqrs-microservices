package org.example.customerquery.controller;

import lombok.RequiredArgsConstructor;
import org.example.customerquery.domain.request.PageCustomerRequest;
import org.example.customerquery.service.CustomerESService;
import org.example.sharedlibrary.response.WrapperResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerESController {

    private final CustomerESService service;

    @GetMapping("/findAll")
    public WrapperResponse findAll(@RequestBody PageCustomerRequest pageRequest) {
        return service.findAll(pageRequest);
    }

    @GetMapping("/detail/{customerId}")
    public WrapperResponse findOne(@PathVariable String customerId) {
        return service.findOneById(customerId);
    }
}
