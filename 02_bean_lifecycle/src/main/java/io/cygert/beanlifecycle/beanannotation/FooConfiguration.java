package io.cygert.beanlifecycle.beanannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FooConfiguration {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    // destroy method can be inferred (method must be named shutdown or close)
    FooBeanAnnotation fooBeanAnnotation() {
        return new FooBeanAnnotation();
    }
}
