package ar.com.jalmeyda.dropwizard.poc.spring;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created by Juan Almeyda on 5/16/2016.
 */
public class SpringContextBuilder {
    private final Map<String, Object> _parentContextBeans;
    private final List<Class> _annotatedClasses;
    private final List<String> _configLocations;

    /**
     * Create a context builder.
     */
    public SpringContextBuilder() {
        _parentContextBeans = Maps.newHashMap();
        _annotatedClasses = Lists.newArrayList();
        _configLocations = Lists.newArrayList();
    }

    /**
     * Adds the specified bean to the parent context.
     *
     * @param name the bean name
     * @param bean the bean instance
     * @return the builder instance
     */
    public SpringContextBuilder addParentContextBean(String name, Object bean) {
        _parentContextBeans.put(name, bean);
        return this;
    }

    /**
     * Adds an annotation-based Spring {@literal @}Configuration class.
     *
     * @param aClass class containing Spring configuration. Should be a class annotated with {@literal @}Configuration.
     * @return the builder instance
     */
    public SpringContextBuilder addAnnotationConfiguration(Class aClass) {
        checkConfigLocationsIsEmpty();
        _annotatedClasses.add(aClass);
        return this;
    }

    /**
     * Adds multiple annotation-based Spring {@literal @}Configuration classes.
     *
     * @param classes the classes containing Spring configuration. Should be classes annotated with
     *                {@literal @}Configuration.
     * @return the builder instance
     */
    public SpringContextBuilder withAnnotationConfigurations(Class... classes) {
        checkConfigLocationsIsEmpty();
        Collections.addAll(_annotatedClasses, classes);
        return this;
    }

    private void checkConfigLocationsIsEmpty() {
        checkState(_configLocations.isEmpty(),
                "XML config locations have already been specified - annotated classes cannot be added!");
    }

    /**
     * Adds a single Spring XML configuration location.
     *
     * @param location the XML config location, e.g. {@code applicationContext.xml}
     * @return the builder instance
     */
    public SpringContextBuilder addXmlConfigLocation(String location) {
        checkAnnotatedClassesIsEmpty();
        _configLocations.add(location);
        return this;
    }

    /**
     * Adds multiple Spring XML configuration locations.
     *
     * @param locations the XML config locations, e.g. {@code applicationContext-core.xml, applicationContext-dao.xml}
     * @return the builder instance
     */
    public SpringContextBuilder withXmlConfigLocations(String... locations) {
        checkAnnotatedClassesIsEmpty();
        Collections.addAll(_configLocations, locations);
        return this;
    }

    private void checkAnnotatedClassesIsEmpty() {
        checkState(_annotatedClasses.isEmpty(),
                "Annotated classes have already been specified - XML config locations cannot be added!");
    }

    /**
     * Generate the {@code ApplicationContext}.
     *
     * @return the {@code ApplicationContext} defined by this builder
     */
    public ApplicationContext build() {
        ApplicationContext parent = buildParentContext();
        return buildContext(parent);
    }

    private ApplicationContext buildParentContext() {
        AnnotationConfigApplicationContext parent = new AnnotationConfigApplicationContext();
        parent.refresh();
        ConfigurableListableBeanFactory beanFactory = parent.getBeanFactory();
        _parentContextBeans.entrySet().stream()
                .forEach(entry -> beanFactory.registerSingleton(entry.getKey(), entry.getValue()));
        parent.registerShutdownHook();
        parent.start();
        return parent;
    }

    private ApplicationContext buildContext(ApplicationContext parent) {
        if (_annotatedClasses.isEmpty()) {
            return buildXmlContext(parent);
        }
        return buildAnnotationContext(parent);
    }

    private ApplicationContext buildAnnotationContext(ApplicationContext parent) {
        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext();
        annotationContext.setParent(parent);
        _annotatedClasses.forEach(annotationContext::register);
        annotationContext.refresh();
        annotationContext.registerShutdownHook();
        annotationContext.start();
        return annotationContext;
    }

    private ApplicationContext buildXmlContext(ApplicationContext parent) {
        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext();
        xmlContext.setParent(parent);
        xmlContext.setConfigLocations(toArray(_configLocations, String.class));
        xmlContext.refresh();
        xmlContext.registerShutdownHook();
        xmlContext.start();
        return xmlContext;
    }

    private <T> T[] toArray(Collection<T> collection, Class<T> clazz) {
        T[] ts = ObjectArrays.newArray(clazz, collection.size());
        return collection.toArray(ts);
    }
}
