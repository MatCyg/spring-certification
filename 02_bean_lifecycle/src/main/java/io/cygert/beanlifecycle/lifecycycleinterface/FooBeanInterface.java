package io.cygert.beanlifecycle.lifecycycleinterface;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class FooBeanInterface implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() {
        log.info("Initialization callback of {} invoked", this.getClass().getSimpleName());
    }

    @Override
    public void destroy() {
        log.info("Destroy callback of {} invoked", this.getClass().getSimpleName());
    }
}
