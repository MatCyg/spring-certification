package io.cygert.contextconfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
class ContextConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContextConfigurationApplication.class, args);
    }
}
