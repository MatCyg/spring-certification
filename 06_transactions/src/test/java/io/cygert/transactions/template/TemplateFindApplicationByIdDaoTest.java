package io.cygert.transactions.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.cygert.transactions.Fixtures.WEATHER_APPLICATION;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TemplateFindApplicationByIdDaoTest {

    @Autowired
    private TemplateFindApplicationByIdDao templateFindApplicationByIdDao;

    @Test
    void findById_should_return_application() {
        // when
        var application = templateFindApplicationByIdDao.findById(1L);

        // then
        assertThat(application).hasValue(WEATHER_APPLICATION);
    }
}