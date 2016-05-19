package ar.com.jalmeyda.dropwizard.poc.service;

import ar.com.jalmeyda.dropwizard.poc.api.Saying;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Juan Almeyda on 5/16/2016.
 */
public class SpringService {

    @Resource(name = "template")
    private String template;
    
    private final AtomicLong counter = new AtomicLong();

    public Saying sayingSomething(String name) {
        final String value = String.format(template, name);
        return new Saying(counter.incrementAndGet(), value);
    }
}
