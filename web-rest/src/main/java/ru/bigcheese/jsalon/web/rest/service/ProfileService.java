package ru.bigcheese.jsalon.web.rest.service;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.ejb.UserFacade;
import ru.bigcheese.jsalon.model.User;
import ru.bigcheese.jsalon.model.to.ProfileTO;
import ru.bigcheese.jsalon.web.rest.RestResponse;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.METHOD_NOT_ALLOWED;

@Path("profile")
public class ProfileService {

    @Inject
    private UserFacade userFacade;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProfile(ProfileTO profile, @Context HttpServletRequest request) throws FacadeException {
        String username = request.getUserPrincipal().getName();
        if (!username.equals(profile.getUsername())) {
            throw new WebApplicationException("Not allowed. " +
                    "Попытка обновить профиль \"" + profile.getUsername() + "\" пользователем " + username,
                    METHOD_NOT_ALLOWED);
        }
        User user = userFacade.updateProfile(profile);
        request.getSession().setAttribute("user", user);
        return RestResponse.ok(new ProfileTO(user));
    }
}
