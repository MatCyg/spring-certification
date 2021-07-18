package io.cygert.transactions.jdbc;

import io.cygert.transactions.model.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
@Repository
@RequiredArgsConstructor
class JdbcInsertApplicationDao {

    private static final String INSERT_APP_SQL =
            "INSERT INTO APPLICATION(NAME) VALUES (?)";
    private static final String INSERT_REVIEW =
            "INSERT INTO REVIEW(APP_ID, RATING, DESCRIPTION) VALUES (?, ?, ?)";

    private final DataSource dataSource;

    public long save(Application app) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            long appId = saveApplication(app, connection);
            saveReviews(app, appId, connection);
            connection.commit();
            return appId;
        } catch (SQLException e) {
            log.error("Failed to save new application", e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    log.error("Failed to rollback transaction", rollbackException);
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Failed to close connection", e);
                }
            }
        }
        throw new RuntimeException("Failed to save application: " + app);
    }

    private long saveApplication(Application app, Connection connection) throws SQLException {
        try (var preparedStatement = connection.prepareStatement(INSERT_APP_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, app.getName());
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows == 0) {
                throw new RuntimeException("Failed to save application: " + app);
            }
            return retrieveId(preparedStatement);
        }
    }

    private long retrieveId(PreparedStatement statement) throws SQLException {
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("Failed to obtains id of created application");
            }
        }
    }

    private void saveReviews(Application app, long appId, Connection connection) throws SQLException {
        try (var preparedStatement = connection.prepareStatement(INSERT_REVIEW)) {
            for (var review : app.getReviews()) {
                preparedStatement.setLong(1, appId);
                preparedStatement.setDouble(2, review.getRating());
                preparedStatement.setString(3, review.getDescription());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }
}
