package ar.com.jalmeyda.dropwizard.poc.service;

import ar.com.jalmeyda.dropwizard.poc.api.Saying;
import ar.com.jalmeyda.dropwizard.poc.dao.SayingDao;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Juan Almeyda on 5/16/2016.
 */
public class SpringService {

    @Resource(name = "template")
    private String template;

    @Resource
    private SayingDao sayingDao;

    private final AtomicLong counter = new AtomicLong();

    public Saying sayingSomething(String name) {
        final String value = String.format(template, name);
        Saying saying = new Saying(counter.incrementAndGet(), value);
        addSaying(saying.getId(), name);
        return saying;
    }

    public Saying getSayingByName(String name) {
        Saying saying = sayingDao.findSayingByName(name).get(0);
        saying.setContent(String.format(template, saying.getContent()));
        return saying;
    }

    public void addSaying(Long id, String name) {
        sayingDao.insert(id, name);
    }
}
