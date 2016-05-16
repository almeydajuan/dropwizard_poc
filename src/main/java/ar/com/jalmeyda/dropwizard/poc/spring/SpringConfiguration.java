package ar.com.jalmeyda.dropwizard.poc.spring;

import ar.com.jalmeyda.dropwizard.poc.HelloWorldConfiguration;
import ar.com.jalmeyda.dropwizard.poc.resource.SpringResource;
import ar.com.jalmeyda.dropwizard.poc.service.SpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Juan Almeyda on 5/16/2016.
 */
@Configuration
public class SpringConfiguration {

    @Autowired
    private HelloWorldConfiguration _configuration;

    @Bean
    public SpringService springService() {
        return new SpringService(_configuration.getTemplate(), _configuration.getDefaultName());
    }

    @Bean
    public SpringResource springResource() {
        return new SpringResource(springService());
    }
    
}
