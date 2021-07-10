package io.cygert.contextconfiguration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ContextConfiguration // loads xml configuration from resources
@ExtendWith(SpringExtension.class)
class XmlContextConfigurationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void shouldContainFooBean() {
        assertThat(context.getBean(Foo.class)).isNotNull();
        assertThatThrownBy(() -> context.getBean(Bar.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}
