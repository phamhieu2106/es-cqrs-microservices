package org.example.insurancequery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.example.insurancedomain.entity")
public class InsuranceQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceQueryApplication.class, args);
    }

}
