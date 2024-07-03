package org.example.customerquery.service;

import org.example.customerquery.domain.request.PageCustomerRequest;
import org.example.sharedlibrary.response.WrapperResponse;

public interface CustomerESService {

    WrapperResponse findOneById(String customerId);

    WrapperResponse findAll(PageCustomerRequest pageRequest);

}
