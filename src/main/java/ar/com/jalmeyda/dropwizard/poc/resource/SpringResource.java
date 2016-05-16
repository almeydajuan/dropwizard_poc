package ar.com.jalmeyda.dropwizard.poc.resource;

import ar.com.jalmeyda.dropwizard.poc.api.Saying;
import ar.com.jalmeyda.dropwizard.poc.service.SpringService;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by Juan Almeyda on 5/16/2016.
 */
@Path("/hello-spring")
@Produces(MediaType.APPLICATION_JSON)
public class SpringResource {

    private SpringService springService;

    public SpringResource(SpringService springService) {
        this.springService = springService;
    }

    @GET
    @Timed
    public Saying helloSpring(@QueryParam("name") Optional<String> name) {
        return springService.sayingSomething(name);
    }
}
