package ar.com.jalmeyda.dropwizard.poc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Juan Almeyda on 5/13/2016.
 */
public class HelloWorldConfiguration extends Configuration {

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }
}
