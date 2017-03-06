package ru.bigcheese.jsalon.web.rest.service;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.ejb.PostFacade;
import ru.bigcheese.jsalon.model.Post;
import ru.bigcheese.jsalon.model.to.PostTO;
import ru.bigcheese.jsalon.web.rest.RestResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Set;

@Path("posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostService {

    @Inject
    private PostFacade postFacade;
    @Context
    private UriInfo uriInfo;

    @GET
    public Response getPosts() {
        List<PostTO> posts = postFacade.findAll();
        return RestResponse.ok(new GenericEntity<List<PostTO>>(posts){});
    }

    @POST
    public Response createPost(PostTO post) throws FacadeException {
        Post created = postFacade.createPost(post);
        return Response
                .created(uriInfo.getAbsolutePathBuilder().path(Long.toString(created.getId())).build())
                .entity(new PostTO(created)).build();
    }

    @PUT
    public Response updatePost(PostTO post) throws FacadeException {
        Post updated = postFacade.updatePost(post);
        return RestResponse.ok(new PostTO(updated));
    }

    @DELETE
    @Path("{id}")
    public Response deletePost(@PathParam("id") Long id) {
        postFacade.deletePost(id);
        return Response.noContent().build();
    }

    @DELETE
    public Response deletePosts(@QueryParam("ids") Set<Long> ids) {
        postFacade.deletePosts(ids);
        return Response.noContent().build();
    }
}
