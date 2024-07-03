package org.example.customerflow.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import lombok.RequiredArgsConstructor;
import org.example.customerdomain.entity.CustomerEntity;
import org.example.customerflow.service.CustomerDocService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CustomerDocServiceImpl implements CustomerDocService {

    private final ElasticsearchClient client;

    @Override
    public void bulkDoc(CustomerEntity customerEntity) throws IOException {
        BulkRequest.Builder br = new BulkRequest.Builder();
        client.bulk(
                br.operations(
                        op -> op
                                .index(idx -> idx
                                        .index("customers")
                                        .id(customerEntity.getId())
                                        .document(customerEntity))
                ).build()
        );
    }
}
