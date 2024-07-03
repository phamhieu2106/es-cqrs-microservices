package org.example.customerquery.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.example.customerdomain.view.CustomerView;
import org.example.customerquery.domain.request.PageCustomerRequest;
import org.example.customerquery.domain.response.CustomerResponse;
import org.example.customerquery.service.CustomerESService;
import org.example.sharedlibrary.constant.PageConstant;
import org.example.sharedlibrary.response.WrapperResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerESServiceImpl implements CustomerESService {

    private final ModelMapper modelMapper;
    private final ElasticsearchTemplate template;
    private final ElasticsearchClient client;

    @Override
    public WrapperResponse findOneById(String customerId) {

        if (customerId == null || customerId.isBlank()) {
            return new WrapperResponse().fail(
                    HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);
        }

        try {
            GetResponse<ObjectNode> response = client.get(g -> g
                            .index("customers")
                            .id(customerId),
                    ObjectNode.class
            );

            if (!response.found()) {
                return new WrapperResponse().fail(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
            }
            ObjectNode json = response.source();
            return new WrapperResponse().success(
                    HttpStatus.OK.getReasonPhrase(), json
            );
        } catch (IOException e) {
            return new WrapperResponse().fail(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @Override
    public WrapperResponse findAll(PageCustomerRequest request) {

        List<CustomerResponse> responses;
        try {
            Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize()
                    , PageConstant.getSortBy(request.getSortBys(), request.getSortOrder()));

            Query query = template.matchAllQuery().setPageable(pageable);

            SearchHits<CustomerView> items = template.search(query, CustomerView.class);

            responses = items.stream()
                    .map(item -> modelMapper.map(item.getContent(), CustomerResponse.class))
                    .toList();
        } catch (Exception e) {
            return new WrapperResponse().fail(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), responses);
    }

}
