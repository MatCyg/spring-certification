package io.cygert.webmvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
class WebMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebMvcApplication.class, args);
    }
}
