package io.cygert.contextconfiguration;

import io.cygert.outside.OutsideMainPackageBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = Foo.class)
@Import(OutsideMainPackageBean.class)
@ExtendWith({SpringExtension.class})
class ImportTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void shouldContainFooBean() {
        assertThat(context.getBean(Foo.class)).isNotNull();
        assertThat(context.getBean(OutsideMainPackageBean.class)).isNotNull();
        assertThatThrownBy(() -> context.getBean(Bar.class))
                  .isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}