package ar.com.jalmeyda.dropwizard.poc.job;

import ar.com.jalmeyda.dropwizard.poc.service.SpringService;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Juan Almeyda on 5/17/2016.
 */
public class SayingJob {

    private SpringService springService;

    public SayingJob(SpringService springService) {
        this.springService = springService;
    }

    @Scheduled(fixedRate = 10000)
    public void doJob() {
        System.out.println("Running Spring Job at " + new DateTime().toString());
        springService.sayingSomething("Spring Job");
    }
}
