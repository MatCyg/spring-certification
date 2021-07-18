package io.cygert.transactions;

import io.cygert.transactions.model.Application;
import io.cygert.transactions.model.Review;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ResultSetToApplicationMapper {

    public Application map(ResultSet resultSet) throws SQLException {
        if (!resultSet.isBeforeFirst() ) {
            return null;
        }
        var builder = Application.builder();
        while (resultSet.next()) {
            builder.id(resultSet.getLong("ID"));
            builder.name(resultSet.getString("NAME"));
            builder.review(mapReview(resultSet));
        }
        return builder.build();
    }

    private Review mapReview(ResultSet resultSet) throws SQLException {
        return Review.builder()
                     .id(resultSet.getLong("REVIEW_ID"))
                     .rating(resultSet.getDouble("RATING"))
                     .description(resultSet.getString("DESCRIPTION"))
                     .build();
    }

}
