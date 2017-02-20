package ru.bigcheese.jsalon.web.rest;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class RestApplication extends ResourceConfig {

    public RestApplication() {
        packages("ru.bigcheese.jsalon.web.rest");
    }
}
