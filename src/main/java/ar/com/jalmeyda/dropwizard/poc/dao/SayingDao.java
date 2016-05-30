package ar.com.jalmeyda.dropwizard.poc.dao;

import ar.com.jalmeyda.dropwizard.poc.api.Saying;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by Juan Almeyda on 5/30/2016.
 */
@RegisterMapper(SayingMapper.class)
public interface SayingDao {

    @SqlUpdate("create table saying (id long primary key, name varchar(20))")
    void createTableIfNotExists();

    @SqlQuery("select id, name from saying where name = :name")
    List<Saying> findSayingByName(@Bind("name") String name);

    @SqlUpdate("insert into saying (id, name) values (:id, :name)")
    void insert(@Bind("id") Long id, @Bind("name") String name);
}
