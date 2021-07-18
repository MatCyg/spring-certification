package io.cygert.transactions.jdbc;

import io.cygert.transactions.ResultSetToApplicationMapper;
import io.cygert.transactions.model.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
class JdbcFindApplicationByIdDao {

    private static final String SQL = """
            SELECT A.*, R.ID AS REVIEW_ID, R.RATING, R.DESCRIPTION FROM APPLICATION A
            LEFT JOIN REVIEW R ON A.ID = APP_ID
            WHERE A.ID = ?
            """;

    private final DataSource dataSource;
    private final ResultSetToApplicationMapper applicationMapper;

    public Optional<Application> findById(Long id) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SQL);
        ) {
            preparedStatement.setLong(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                return Optional.of(applicationMapper.map(resultSet));
            }
        } catch (SQLException e) {
            log.error("Failed to find application by id", e);
        }
        return Optional.empty();
    }


}
