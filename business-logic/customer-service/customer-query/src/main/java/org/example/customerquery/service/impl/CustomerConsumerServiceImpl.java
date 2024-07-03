package org.example.customerquery.service.impl;

import org.example.customerquery.service.CustomerConsumerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerConsumerServiceImpl implements CustomerConsumerService {

    @Override
//    @KafkaListener(topics = "customer_server.public.customer_entity", groupId = "")
    public void listenCDC(String message) {

    }
}
