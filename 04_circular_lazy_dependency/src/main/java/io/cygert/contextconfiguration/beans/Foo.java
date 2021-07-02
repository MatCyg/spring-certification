package io.cygert.contextconfiguration.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Foo {

    private final Bar bar;

    Foo(@Lazy Bar bar) {
        log.info("Invoked Foo Constructor");
        log.info("Bar {}", bar.getClass());
        this.bar = bar;
    }

    public void printFooBar() {
        log.info("Foo{}", bar.getBar());
    }
}
