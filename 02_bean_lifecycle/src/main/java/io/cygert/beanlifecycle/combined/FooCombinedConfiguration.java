package io.cygert.beanlifecycle.combined;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FooCombinedConfiguration {

    @Bean(initMethod = "beanInit", destroyMethod = "beanDestroy")
    FooCombined fooCombined() {
        return new FooCombined();
    }
}
