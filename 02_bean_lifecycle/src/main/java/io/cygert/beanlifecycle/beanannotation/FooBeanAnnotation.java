package io.cygert.beanlifecycle.beanannotation;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;

@Slf4j
class FooBeanAnnotation {

    void init() {
        log.info("Initialization callback of {} invoked", this.getClass().getSimpleName());
    }

    void destroy() {
        log.info("Destroy callback of {} invoked", this.getClass().getSimpleName());
    }

}
