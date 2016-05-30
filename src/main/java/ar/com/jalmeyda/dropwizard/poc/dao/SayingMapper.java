package ar.com.jalmeyda.dropwizard.poc.dao;

import ar.com.jalmeyda.dropwizard.poc.api.Saying;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Juan Almeyda on 5/30/2016.
 */
public class SayingMapper implements ResultSetMapper<Saying> {

    @Override
    public Saying map(int i, ResultSet resultSet, StatementContext statementContext)
            throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return new Saying(id, name);
    }
}
