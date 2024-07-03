package org.example.usercommand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.example.userdomain.entity")
public class UserCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCommandApplication.class, args);
    }

}
