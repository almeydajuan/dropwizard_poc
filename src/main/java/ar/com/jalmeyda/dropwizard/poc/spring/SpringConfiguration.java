package ar.com.jalmeyda.dropwizard.poc.spring;

import ar.com.jalmeyda.dropwizard.poc.HelloWorldConfiguration;
import ar.com.jalmeyda.dropwizard.poc.job.SayingJob;
import ar.com.jalmeyda.dropwizard.poc.resource.SpringResource;
import ar.com.jalmeyda.dropwizard.poc.service.SpringService;
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

}
