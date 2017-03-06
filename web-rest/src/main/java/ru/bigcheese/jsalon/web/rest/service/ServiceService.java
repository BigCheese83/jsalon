package ru.bigcheese.jsalon.web.rest.service;

import ru.bigcheese.jsalon.ejb.ServiceFacade;
import ru.bigcheese.jsalon.model.to.ServiceTO;
import ru.bigcheese.jsalon.web.rest.RestResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("services")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceService {

    @Inject
    private ServiceFacade serviceFacade;

    @GET
    public Response getServicesByPostId(@QueryParam("postId") Long postId) {
        List<ServiceTO> services = serviceFacade.getServicesByPostId(postId);
        return RestResponse.ok(new GenericEntity<List<ServiceTO>>(services){});
    }
}
