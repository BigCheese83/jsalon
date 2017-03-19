package ru.bigcheese.jsalon.web.rest.service;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.ejb.ServiceFacade;
import ru.bigcheese.jsalon.model.Service;
import ru.bigcheese.jsalon.model.to.ServiceTO;
import ru.bigcheese.jsalon.web.rest.RestResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Set;

@Path("services")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceService {

    @Inject
    private ServiceFacade serviceFacade;
    @Context
    private UriInfo uriInfo;

    @GET
    public Response getServices() {
        List<ServiceTO> services = serviceFacade.findAll();
        return RestResponse.ok(new GenericEntity<List<ServiceTO>>(services) {
        });
    }

    @GET
    @Path("posts/{postId}")
    public Response getServicesByPostId(@PathParam("postId") Long postId) {
        List<ServiceTO> services = serviceFacade.getServicesByPostId(postId);
        return RestResponse.ok(new GenericEntity<List<ServiceTO>>(services) {
        });
    }

    @POST
    public Response createService(ServiceTO service) throws FacadeException {
        Service created = serviceFacade.createService(service);
        return Response
                .created(uriInfo.getAbsolutePathBuilder().path(Long.toString(created.getId())).build())
                .entity(new ServiceTO(created)).build();
    }

    @PUT
    public Response updateService(ServiceTO service) throws FacadeException {
        Service updated = serviceFacade.updateService(service);
        return RestResponse.ok(new ServiceTO(updated));
    }

    @DELETE
    @Path("{id}")
    public Response deleteService(@PathParam("id") Long id) {
        serviceFacade.deleteService(id);
        return Response.noContent().build();
    }

    @DELETE
    public Response deleteServices(@QueryParam("ids") Set<Long> ids) {
        serviceFacade.deleteServices(ids);
        return Response.noContent().build();
    }
}