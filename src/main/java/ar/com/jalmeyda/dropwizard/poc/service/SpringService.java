package ar.com.jalmeyda.dropwizard.poc.service;

import ar.com.jalmeyda.dropwizard.poc.api.Saying;
import com.google.common.base.Optional;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Juan Almeyda on 5/16/2016.
 */
public class SpringService {

    private String template;
    private String defaultName;
    private final AtomicLong counter = new AtomicLong();

    public SpringService(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
    }

    public Saying sayingSomething(Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}
