package ru.bigcheese.jsalon.web.rest;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.model.to.ErrorTO;

import javax.ejb.EJBException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.*;
import static ru.bigcheese.jsalon.core.CommonUtils.parseException;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {
        int status = INTERNAL_SERVER_ERROR.getStatusCode();

        while (isSkipException(ex) && ex.getCause() != null) {
            ex = ex.getCause();
        }

        if (ex instanceof NullPointerException || ex instanceof IllegalArgumentException) {
            status = BAD_REQUEST.getStatusCode();
        } else if (ex instanceof WebApplicationException) {
            status = ((WebApplicationException) ex).getResponse().getStatus();
        } else if (ex instanceof FacadeException) {
            status = getStatusFromFacadeException((FacadeException) ex);
        }

        return Response.status(status)
                .entity(new ErrorTO(parseException(ex), status))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private boolean isSkipException(Throwable ex) {
        return ex instanceof EJBException;
    }

    private int getStatusFromFacadeException(FacadeException ex) {
        if (ex.getKey() == null) {
            return INTERNAL_SERVER_ERROR.getStatusCode();
        }
        switch (ex.getKey()) {
            case ENTITY_NOT_FOUND:
                return NOT_FOUND.getStatusCode();
            case INVALID_CREDENTIALS:
                return UNAUTHORIZED.getStatusCode();
            case SET_INVALID_PARAMETER:
                return BAD_REQUEST.getStatusCode();
            default:
                return INTERNAL_SERVER_ERROR.getStatusCode();
        }
    }
}
