package ar.com.jalmeyda.dropwizard.poc.resource;

import ar.com.jalmeyda.dropwizard.poc.api.HelloRequestBody;
import ar.com.jalmeyda.dropwizard.poc.api.Saying;
import ar.com.jalmeyda.dropwizard.poc.service.SpringService;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Juan Almeyda on 5/16/2016.
 */
@Api("/hello-spring")
@Path("/hello-spring")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpringResource {

    @Resource
    private SpringService springService;

    @Resource(name = "defaultName")
    private String defaultName;

    @POST
    @Timed
    @ApiOperation(value = "Get greeted", response = Saying.class)
    public Saying helloSpring(@ApiParam(value = "helloRequestBody") @Valid HelloRequestBody helloRequestBody) {
        return springService.sayingSomething(helloRequestBody.getName());
    }

    @GET
    @Timed
    @ApiOperation(value = "Get saying", response = Saying.class)
    public Saying helloSpring(@QueryParam("name") String name) {
        return springService.getSayingByName(name);
    }
}
