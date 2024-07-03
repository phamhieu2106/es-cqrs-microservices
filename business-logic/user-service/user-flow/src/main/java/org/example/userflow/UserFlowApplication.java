package org.example.userflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.example.userdomain.entity")
public class UserFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserFlowApplication.class, args);
    }

}
