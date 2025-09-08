package org.agoncal.fascicle.quarkus.book.exception;
/*
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class ExceptionsMappers {

    @Inject
    Logger auditor;

    @ServerExceptionMapper
    public Response mapHttpNoContentException(HttpNoContentException x) {

        auditor.debug("mapHttpNoContentException");
        return Response.noContent().build();
    }

    @ServerExceptionMapper
    public Response mapHttpConflictException(HttpConflictException x) {

        auditor.debug("mapHttpConflictException: " + x.getMessage());
        return Response.status(Response.Status.CONFLICT).header("Warning",x.getMessage()).build();
    }

    @ServerExceptionMapper
    public Response mapHttpBadRequestException(HttpBadResquestException x) {

        auditor.debug("mapHttpConflictException: " + x.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).header("Warning", x.getMessage()).build();
    }

    @ServerExceptionMapper
    public Response mapHttpNotFoundException(HttpNotFoundException x) {

        auditor.debug("mapHttpNotFoundException: " + x.getMessage());
        return Response.status(Response.Status.NOT_FOUND).header("Warning",x.getMessage()).build();
    }


}
*/
