package io.cygert.transactions.template;

import io.cygert.transactions.ResultSetToApplicationMapper;
import io.cygert.transactions.model.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
class ApplicationExtractor implements ResultSetExtractor<Application> {

    private final ResultSetToApplicationMapper applicationMapper;

    @Override
    public Application extractData(ResultSet rs) throws SQLException, DataAccessException {
        return applicationMapper.map(rs);
    }
}
