package io.cygert.transactions.template;

import io.cygert.transactions.model.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Statement;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
class TemplateInsertApplicationDao {

    private static final String INSERT_APP_SQL =
            "INSERT INTO APPLICATION(NAME) VALUES (?)";
    private static final String INSERT_REVIEW =
            "INSERT INTO REVIEW(APP_ID, RATING, DESCRIPTION) VALUES (?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public long save(Application app) {
        var appId = saveApp(app);
        saveReviews(app, appId);
        return appId;
    }

    private long saveApp(Application app) {
        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var preparedStatement = connection.prepareStatement(INSERT_APP_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, app.getName());
            log.info("SQL = '{}'", preparedStatement);
            return preparedStatement;
        }, keyHolder);

        return retrieveAppId(keyHolder);
    }

    private long retrieveAppId(KeyHolder keyHolder) {
        var key = keyHolder.getKey();
        if(key == null) {
            throw new RuntimeException("Failed to save application, id not found");
        }
        return key.longValue();
    }

    private void saveReviews(Application app, long appId) {
        jdbcTemplate.batchUpdate(INSERT_REVIEW, getReviewArgs(app, appId));
    }

    private List<Object[]> getReviewArgs(Application app, long appId) {
        return app.getReviews()
                  .stream()
                  .map(review -> new Object[]{appId, review.getRating(), review.getDescription()})
                  .toList();
    }
}
