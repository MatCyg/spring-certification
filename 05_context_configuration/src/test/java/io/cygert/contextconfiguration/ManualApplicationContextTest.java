package io.cygert.contextconfiguration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualApplicationContextTest {

    @Test
    void shouldContainFooBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Foo.class);

        assertThat(context.getBean(Foo.class)).isNotNull();
        assertThatThrownBy(() -> context.getBean(Bar.class))
                  .isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}