package org.example.userquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.example.userdomain.entity")
public class UserQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserQueryApplication.class, args);
    }

}
