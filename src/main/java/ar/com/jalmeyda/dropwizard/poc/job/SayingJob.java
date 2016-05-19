package ar.com.jalmeyda.dropwizard.poc.job;

import ar.com.jalmeyda.dropwizard.poc.service.SpringService;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * Created by Juan Almeyda on 5/17/2016.
 */
public class SayingJob {

    @Resource
    private SpringService springService;

    @Scheduled(fixedRate = 10000)
    public void doJob() {
        System.out.println("Running Spring Job at " + new DateTime().toString());
        springService.sayingSomething("Spring Job");
    }
}
