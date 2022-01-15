package io.cygert.transactions.template;

import io.cygert.transactions.model.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static io.cygert.transactions.Fixtures.newCalculatorApplication;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TemplateInsertApplicationDaoTest {

    @Autowired
    private TemplateInsertApplicationDao insertApplication;
    @Autowired
    private TemplateFindApplicationByIdDao findApplicationByIdDaoById;

    @Test
    @Transactional
    void save_shouldAddNewApplication() {
        // given
        Application application = newCalculatorApplication();
        // when
        long appId = insertApplication.save(application);

        // then
        assertThat(findApplicationByIdDaoById.findById(appId)).hasValueSatisfying(savedApplication -> {
            assertThat(savedApplication.getId()).isEqualTo(appId);
            assertThat(savedApplication.getName()).isEqualTo(application.getName());
            assertThat(savedApplication.getReviews()).usingElementComparatorIgnoringFields("id")
                                                     .containsAll(application.getReviews());
        });
    }

}
