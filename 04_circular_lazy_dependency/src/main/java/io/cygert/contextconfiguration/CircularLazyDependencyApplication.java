package io.cygert.contextconfiguration;

import io.cygert.contextconfiguration.beans.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@Slf4j
@SpringBootApplication
class CircularLazyDependencyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CircularLazyDependencyApplication.class, args);

        Foo foo = context.getBean(Foo.class);
        log.info("Before printing FooBar");
        foo.printFooBar();
        log.info("After printing FooBar");
    }
}
