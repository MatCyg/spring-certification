package io.cygert.contextconfiguration.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Slf4j
@Component
class Bar {

    private final Foo foo;

    Bar(Foo foo) {
        log.info("Invoked Bar Constructor");
        this.foo = foo;
    }

    String getBar() {
        return "Bar";
    }
}
