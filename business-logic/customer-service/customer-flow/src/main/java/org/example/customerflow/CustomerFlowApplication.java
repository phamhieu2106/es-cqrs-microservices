package org.example.customerflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.example.customerdomain.entity")
public class CustomerFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerFlowApplication.class, args);
    }

}
