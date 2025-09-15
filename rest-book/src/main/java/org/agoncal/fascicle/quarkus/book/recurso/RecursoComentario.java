package org.agoncal.fascicle.quarkus.book.recurso;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.agoncal.fascicle.quarkus.book.servicio.ServicioComentario;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleComentario;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleComentarioCrear;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibro;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibroCrear;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Comment Endpoint")
public class RecursoComentario {

  @Inject
  ServicioComentario service;

  private static final Logger LOGGER = Logger.getLogger(RecursoLibro.class);

  @Operation(summary = "Creates a valid comment")
  @APIResponse(responseCode = "201", description = "The URI of the created comment", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleComentario.class)))
  @Counted(name = "countCreateComment", description = "Counts how many times the createComment method has been invoked")
  @Timed(name = "timeCreateComment", description = "Times how long it takes to invoke the createComment method", unit = MetricUnits.MILLISECONDS)
  @POST
  public Response createComment(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleComentarioCrear.class))) @Valid TransferibleComentarioCrear dto, @Context UriInfo uriInfo) {
    TransferibleComentario comentarioCreado = service.createComentario(dto);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(comentarioCreado.getId()));
    LOGGER.debug("New comment created with URI " + builder.build().toString());
    return Response.created(builder.build()).entity(comentarioCreado).build();
  }

  @Operation(summary = "Returns all the comments from the database")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleComentario.class, type = SchemaType.ARRAY)))
  @APIResponse(responseCode = "204", description = "No comments")
  @Counted(name = "countGetAllComments", description = "Counts how many times the getAllComments method has been invoked")
  @Timed(name = "timeGetAllComments", description = "Times how long it takes to invoke the getAllComments method", unit = MetricUnits.MILLISECONDS)
  @GET
  public Response getAllComments() {
    List<TransferibleComentario> comments = service.findAllComentarios();
    LOGGER.debug("Total number of comments " + comments);
    return Response.ok(comments).build();
  }

  @Operation(summary = "Returns a comment for a given identifier")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleComentario.class)))
  @APIResponse(responseCode = "404", description = "The comment is not found for the given identifier")
  @Counted(name = "countGetComment", description = "Counts how many times the getComment method has been invoked")
  @Timed(name = "timeGetComment", description = "Times how long it takes to invoke the getComment method", unit = MetricUnits.MILLISECONDS)
  @GET
  @Path("/{id}")
  public Response getComment(@Parameter(description = "Comment identifier", required = true) @PathParam("id") Long id) {
    Optional<TransferibleComentario> existente = service.findComentarioById(id);
    if (existente.isPresent()) {
      LOGGER.debug("Found comment " + existente);
      return Response.ok(existente).build();
    } else {
      LOGGER.debug("No comment found with id " + id);
      return Response.status(NOT_FOUND).build();
    }
  }

  @Operation(summary = "Updates an existing comment")
  @APIResponse(responseCode = "200", description = "The updated comment", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleComentario.class)))
  @Counted(name = "countUpdateComment", description = "Counts how many times the updateComment method has been invoked")
  @Timed(name = "timeUpdateComment", description = "Times how long it takes to invoke the updateComment method", unit = MetricUnits.MILLISECONDS)
  @PUT
  @Path("/{id}")
  public Response updateComment(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleComentarioCrear.class))) @PathParam("id") Long id, @Valid TransferibleComentarioCrear dto) {
    TransferibleComentario comentarioActualizado = service.updateComentario(id, dto);
    LOGGER.debug("Cooment updated with new valued " + comentarioActualizado);
    return Response.ok(comentarioActualizado).build();
  }

  @Operation(summary = "Deletes an existing comment")
  @APIResponse(responseCode = "204", description = "The comment has been successfully deleted")
  @Counted(name = "countDeleteComment", description = "Counts how many times the deleteComment method has been invoked")
  @Timed(name = "timeDeleteComment", description = "Times how long it takes to invoke the deleteComment method", unit = MetricUnits.MILLISECONDS)
  @DELETE
  @Path("/{id}")
  public Response deleteComment(@Parameter(description = "Cooment identifier", required = true) @PathParam("id") Long id) {
    service.deleteComentario(id);
    LOGGER.debug("Comment deleted with " + id);
    return Response.noContent().build();
  }
}
