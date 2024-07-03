package org.example.contractcommand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan("org.example.contractdomain.entity")
public class ContractCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContractCommandApplication.class, args);
    }

}
