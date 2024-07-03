package org.example.customercommand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan("org.example.customerdomain.entity")
public class CustomerCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerCommandApplication.class, args);
    }

}
