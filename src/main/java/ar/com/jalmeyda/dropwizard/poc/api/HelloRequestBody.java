package ar.com.jalmeyda.dropwizard.poc.api;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Juan Almeyda on 5/30/2016.
 */
public class HelloRequestBody {

    @NotNull
    String name;

    public String getName() {
        return name;
    }
}
