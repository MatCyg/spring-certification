package io.cygert.transactions.template;

import io.cygert.transactions.model.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
class TemplateFindApplicationByIdDao {

    private static final String SQL = """
            SELECT A.*, R.ID AS REVIEW_ID, R.RATING, R.DESCRIPTION FROM APPLICATION A
            LEFT JOIN REVIEW R ON A.ID = APP_ID
            WHERE A.ID = ?
            """;

    private final JdbcTemplate jdbcTemplate;
    private final ApplicationExtractor applicationExtractor;

    public Optional<Application> findById(Long id) {
        Application application = jdbcTemplate.query(SQL, applicationExtractor, id);
        return Optional.ofNullable(application);
    }
}
