package org.agoncal.fascicle.quarkus.book.recurso;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.agoncal.fascicle.quarkus.book.servicio.ServicioAutor;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleAutor;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleAutorCrear;
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
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Author Endpoint")
public class RecursoAutor {

  @Inject
  ServicioAutor service;

  private static final org.jboss.logging.Logger LOGGER = Logger.getLogger(RecursoAutor.class);

  @Operation(summary = "Creates a valid author")
  @APIResponse(responseCode = "201", description = "The URI of the created author", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleAutor.class)))
  @Counted(name = "countCreateAuthor", description = "Counts how many times the createAuthor method has been invoked")
  @Timed(name = "timeCreateAuthor", description = "Times how long it takes to invoke the createAuthor method", unit = MetricUnits.MILLISECONDS)
  @POST
  public Response createAuthor(@Valid TransferibleAutorCrear dto, @Context UriInfo uriInfo) {
    TransferibleAutor autorCreado = service.persistAutor(dto);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(autorCreado.getId()));
    LOGGER.debug("New author created with URI " + builder.build().toString());
    return Response.created(builder.build()).entity(autorCreado).build();
  }

  @Operation(summary = "Returns all the authors from the database")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleAutor.class, type = SchemaType.ARRAY)))
  @APIResponse(responseCode = "204", description = "No authors")
  @Counted(name = "countGetAllAuthors", description = "Counts how many times the getAllAuthors method has been invoked")
  @Timed(name = "timeGetAllAuthors", description = "Times how long it takes to invoke the getAllAuthors method", unit = MetricUnits.MILLISECONDS)
  @GET
  public Response getAllAuthors() {
    List<TransferibleAutor> autores = service.findAllAutores();
    LOGGER.debug("Total number of authors " + autores);
    return Response.ok(autores).build();
  }

  @Operation(summary = "Returns an author for a given identifier")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleAutor.class)))
  @APIResponse(responseCode = "404", description = "The author is not found for the given identifier")
  @Counted(name = "countGetAuthor", description = "Counts how many times the getAuthor method has been invoked")
  @Timed(name = "timeGetAuthor", description = "Times how long it takes to invoke the getAuthor method", unit = MetricUnits.MILLISECONDS)
  @GET
  @Path("/{id}")
  public Response getAuthor(@Parameter(description = "Author identifier", required = true) @PathParam("id") Long id) {
    Optional<TransferibleAutor> author = service.findAutorById(id);
    if (author.isPresent()) {
      LOGGER.debug("Found author " + author);
      return Response.ok(author).build();
    } else {
      LOGGER.debug("No author found with id " + id);
      return Response.status(NOT_FOUND).build();
    }
  }

  @Operation(summary = "Updates an existing author")
  @APIResponse(responseCode = "200", description = "The updated author", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleAutor.class)))
  @Counted(name = "countUpdateAuthor", description = "Counts how many times the updateAuthor method has been invoked")
  @Timed(name = "timeUpdateAuthor", description = "Times how long it takes to invoke the updateAuthor method", unit = MetricUnits.MILLISECONDS)
  @PUT
  @Path("/{id}")
  public Response updateAuthor(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleAutorCrear.class))) @PathParam("id") Long id, @Valid TransferibleAutorCrear dto) {
    TransferibleAutor actualizado = service.updateAutor(id, dto);
    LOGGER.debug("Author updated with new valued " + actualizado);
    return Response.ok(actualizado).build();
  }

  @Operation(summary = "Deletes an existing author")
  @APIResponse(responseCode = "204", description = "The author has been successfully deleted")
  @Counted(name = "countDeleteAuthor", description = "Counts how many times the deleteBook method has been invoked")
  @Timed(name = "timeDeleteAuthor", description = "Times how long it takes to invoke the deleteBook method", unit = MetricUnits.MILLISECONDS)
  @DELETE
  @Path("/{id}")
  public Response deleteAuthor(@Parameter(description = "Author identifier", required = true) @PathParam("id") Long id) {
    service.deleteAutor(id);
    LOGGER.debug("Author deleted with " + id);
    return Response.noContent().build();
  }
}
