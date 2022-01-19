package com.example.emrestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ServletComponentScan("com.example.emrestserver.security.filter")

public class EmRestServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmRestServerApplication.class, args);
    }

}
