package ru.bigcheese.jsalon.web.rest.service;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.ejb.DiscountFacade;
import ru.bigcheese.jsalon.model.Discount;
import ru.bigcheese.jsalon.model.to.DiscountTO;
import ru.bigcheese.jsalon.web.rest.RestResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Set;

@Path("discounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DiscountService {

    @Inject
    private DiscountFacade discountFacade;
    @Context
    private UriInfo uriInfo;

    @GET
    public Response getDiscounts() {
        List<DiscountTO> discounts = discountFacade.findAll();
        return RestResponse.ok(new GenericEntity<List<DiscountTO>>(discounts){});
    }

    @POST
    public Response createDiscount(DiscountTO discount) throws FacadeException {
        Discount created = discountFacade.createDiscount(discount);
        return Response
                .created(uriInfo.getAbsolutePathBuilder().path(Long.toString(created.getId())).build())
                .entity(new DiscountTO(created)).build();
    }

    @PUT
    public Response updateDiscount(DiscountTO discount) throws FacadeException {
        Discount updated = discountFacade.updateDiscount(discount);
        return RestResponse.ok(new DiscountTO(updated));
    }

    @DELETE
    @Path("{id}")
    public Response deleteDiscount(@PathParam("id") Long id) {
        discountFacade.deleteDiscount(id);
        return Response.noContent().build();
    }

    @DELETE
    public Response deleteDiscounts(@QueryParam("ids") Set<Long> ids) {
        discountFacade.deleteDiscounts(ids);
        return Response.noContent().build();
    }
}
