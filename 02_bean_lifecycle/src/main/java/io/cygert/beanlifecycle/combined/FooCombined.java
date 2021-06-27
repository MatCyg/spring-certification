package io.cygert.beanlifecycle.combined;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
class FooCombined implements InitializingBean, DisposableBean {

    @PostConstruct
    void init() {
        log.info("@PostConstruct of {} invoked", this.getClass().getSimpleName());
    }

    @PreDestroy
    void preDestroy() {
        log.info("@PreDestroy of {} invoked", this.getClass().getSimpleName());
    }

    // invoked by @Bean declaration

    void beanInit() {
        log.info("@Bean init of {} invoked", this.getClass().getSimpleName());
    }

    void beanDestroy() {
        log.info("@Bean destroy of {} invoked", this.getClass().getSimpleName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitializingBean::afterPropertiesSet of {} invoked", this.getClass().getSimpleName());
    }

    @Override
    public void destroy() throws Exception {
        log.info("DisposableBean::destroy of {} invoked", this.getClass().getSimpleName());
    }
}
