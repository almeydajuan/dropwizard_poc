package ar.com.jalmeyda.dropwizard.poc.spring;

import ar.com.jalmeyda.dropwizard.poc.HelloWorldConfiguration;
import ar.com.jalmeyda.dropwizard.poc.dao.SayingDao;
import ar.com.jalmeyda.dropwizard.poc.job.SayingJob;
import ar.com.jalmeyda.dropwizard.poc.resource.SpringResource;
import ar.com.jalmeyda.dropwizard.poc.service.SpringService;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Juan Almeyda on 5/16/2016.
 */
@Configuration
@EnableScheduling
public class SpringConfiguration {

    @Autowired
    private HelloWorldConfiguration _configuration;

    @Autowired
    private Environment _dropwizardEnvironment;

    @Bean(name = "defaultName")
    public String defaultName() {
        return _configuration.getDefaultName();
    }

    @Bean(name = "template")
    public String template() {
        return _configuration.getTemplate();
    }

    @Bean
    public SpringService springService() {
        return new SpringService();
    }

    @Bean
    public SpringResource springResource() {
        return new SpringResource();
    }

    @Bean
    public SayingJob sayingJob() {
        return new SayingJob();
    }

    @Bean
    public DBI dbi() {
        return new DBIFactory().build(_dropwizardEnvironment, _configuration.getDataSourceFactory(), "juan");
    }

    @Bean
    public SayingDao sayingDao() {
        SayingDao sayingDao = dbi().onDemand(SayingDao.class);
        sayingDao.createTableIfNotExists();
        return sayingDao;
    }

}
