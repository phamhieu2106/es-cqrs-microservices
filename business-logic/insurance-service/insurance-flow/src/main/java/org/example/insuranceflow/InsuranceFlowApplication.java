package org.example.insuranceflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.example.insurancedomain.entity")
public class InsuranceFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceFlowApplication.class, args);
    }

}
