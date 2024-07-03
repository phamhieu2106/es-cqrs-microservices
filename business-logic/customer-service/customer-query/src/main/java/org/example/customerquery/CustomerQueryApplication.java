package org.example.customerquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"org.example.customerdomain.entity", "org.example.customerdomain.view"})
public class CustomerQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerQueryApplication.class, args);
    }

}
