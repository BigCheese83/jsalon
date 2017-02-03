package ru.bigcheese.jsalon.web.rest;

import javax.ws.rs.core.Response;

public class RestResponse {

    public static <E> Response ok(E entity) {
        if (entity == null) {
            return Response.noContent().build();
        } else {
            return Response.ok(entity).build();
        }
    }
}
