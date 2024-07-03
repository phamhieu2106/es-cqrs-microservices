package org.example.customerflow.service;

import org.example.customerdomain.entity.CustomerEntity;

import java.io.IOException;

public interface CustomerDocService {

    void bulkDoc(CustomerEntity customerEntity) throws IOException;
}
