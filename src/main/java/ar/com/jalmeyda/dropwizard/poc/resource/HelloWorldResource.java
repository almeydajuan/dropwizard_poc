package ar.com.jalmeyda.dropwizard.poc.resource;

import ar.com.jalmeyda.dropwizard.poc.api.Saying;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Juan Almeyda on 5/16/2016.
 */
@Api("/hello-world")
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @ApiOperation(value = "Get greeted", response = Saying.class)
    public Saying sayHello(@QueryParam("name") String name) {
        final String value = String.format(template, name == null ? defaultName : name);
        return new Saying(counter.incrementAndGet(), value);
    }
}
