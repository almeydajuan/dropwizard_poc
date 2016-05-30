package ar.com.jalmeyda.dropwizard.poc;

import ar.com.jalmeyda.dropwizard.poc.health.PingHealthCheck;
import ar.com.jalmeyda.dropwizard.poc.resource.HelloWorldResource;
import ar.com.jalmeyda.dropwizard.poc.resource.SpringResource;
import ar.com.jalmeyda.dropwizard.poc.spring.SpringConfiguration;
import ar.com.jalmeyda.dropwizard.poc.spring.SpringContextBuilder;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * Created by Juan Almeyda on 5/13/2016.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<HelloWorldConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(HelloWorldConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {
        final PingHealthCheck healthCheck = new PingHealthCheck();
        environment.healthChecks().register("ping", healthCheck);

        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        // Spring context
        ApplicationContext context = new SpringContextBuilder()
                .addParentContextBean("configuration", configuration)
                .addAnnotationConfiguration(SpringConfiguration.class)
                .build();
        SpringResource springResource = context.getBean(SpringResource.class);
        environment.jersey().register(springResource);
    }
}
