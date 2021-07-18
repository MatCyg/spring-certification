package io.cygert.transactions.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.cygert.transactions.Fixtures.WEATHER_APPLICATION;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JdbcFindApplicationByIdDaoTest {

    @Autowired
    private JdbcFindApplicationByIdDao jdbcFindApplicationByIdDao;

    @Test
    void findById_should_return_application() {
        // when
        var application = jdbcFindApplicationByIdDao.findById(1L);

        // then
        assertThat(application).hasValue(WEATHER_APPLICATION);
    }
}