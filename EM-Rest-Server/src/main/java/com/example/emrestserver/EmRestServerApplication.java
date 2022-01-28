package com.example.emrestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
//@ServletComponentScan("com.example.emrestserver.security.filter")
@EnableAsync
public class EmRestServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmRestServerApplication.class, args);
    }

}
