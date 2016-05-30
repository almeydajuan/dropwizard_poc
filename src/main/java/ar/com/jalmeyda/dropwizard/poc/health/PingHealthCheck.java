package ar.com.jalmeyda.dropwizard.poc.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by Juan Almeyda on 5/16/2016.
 */
public class PingHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy("pong");
    }
}
