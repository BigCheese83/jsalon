package ru.bigcheese.jsalon.web.rest;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import ru.bigcheese.jsalon.web.rest.service.ProfileService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
public class RestApplication extends Application {

    private final Set<Class<?>> classes;

    public RestApplication() {
        Set<Class<?>> c = new HashSet<>();
        c.add(ProfileService.class);
        c.add(GeneralExceptionMapper.class);
        c.add(MOXyJsonProvider.class);
        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
