package io.cygert.beanlifecycle.annotation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
@RequiredArgsConstructor
class FooAnnotation {

    private final FooAnnotation2 fooAnnotation2;

    @PostConstruct
    void init() {
        log.info("Initialization callback of {} invoked", this.getClass().getSimpleName());
    }

    @PreDestroy
    void destroy() {
        log.info("Destroy callback of {} invoked", this.getClass().getSimpleName());
    }

}
