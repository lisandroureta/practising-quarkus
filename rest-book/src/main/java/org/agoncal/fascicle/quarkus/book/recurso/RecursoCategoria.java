package org.agoncal.fascicle.quarkus.book.recurso;

import io.quarkus.security.Authenticated;
import jakarta.ws.rs.*;
import org.agoncal.fascicle.quarkus.book.modelo.Book;
import org.agoncal.fascicle.quarkus.book.servicio.ServicioCategoria;
import org.agoncal.fascicle.quarkus.book.servicio.ServicioLibro;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleCategoria;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleCategoriaCrear;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibro;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibroCrear;
import org.agoncal.fascicle.quarkus.book.transformador.TransformadorLibro;
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

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Category Endpoint")

public class RecursoCategoria {

  @Inject
  ServicioCategoria service;

  private static final Logger LOGGER = Logger.getLogger(RecursoCategoria.class);

  @Operation(summary = "Creates a valid category")
  @APIResponse(responseCode = "201", description = "The URI of the created category", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleCategoria.class)))
  @Counted(name = "countCreateCategory", description = "Counts how many times the createCategory method has been invoked")
  @Timed(name = "timeCreateCategory", description = "Times how long it takes to invoke the createCategory method", unit = MetricUnits.MILLISECONDS)
  @POST
  public Response createCategory(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleCategoriaCrear.class))) @Valid TransferibleCategoriaCrear dto, @Context UriInfo uriInfo) {
    TransferibleCategoria categoriaCreada = service.createCategoria(dto);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(categoriaCreada.getId()));
    LOGGER.debug("New category created with URI " + builder.build().toString());
    return Response.created(builder.build()).entity(categoriaCreada).build();
  }

  @Operation(summary = "Returns all the categories from the database")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleCategoria.class, type = SchemaType.ARRAY)))
  @APIResponse(responseCode = "204", description = "No categorie")
  @Counted(name = "countGetAllCategories", description = "Counts how many times the getAllCategories method has been invoked")
  @Timed(name = "timeGetAllCategories", description = "Times how long it takes to invoke the getAllCategories method", unit = MetricUnits.MILLISECONDS)
  @GET
  public Response getAllCategories() {
    List<TransferibleCategoria> categorias = service.findAllCategorias();
    LOGGER.debug("Total number of categories " + categorias);
    return Response.ok(categorias).build();
  }

  @Operation(summary = "Returns a category for a given identifier")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleCategoria.class)))
  @APIResponse(responseCode = "404", description = "The category is not found for the given identifier")
  @Counted(name = "countGetBook", description = "Counts how many times the getCategory method has been invoked")
  @Timed(name = "timeGetBook", description = "Times how long it takes to invoke the getCategory method", unit = MetricUnits.MILLISECONDS)
  @GET
  @Path("/{id}")
  public Response getCategory(@Parameter(description = "Book identifier", required = true) @PathParam("id") Long id) {
    Optional<TransferibleCategoria> categoria = service.findCategoriaById(id);
    if (categoria.isPresent()) {
      LOGGER.debug("Found category " + categoria);
      return Response.ok(categoria).build();
    } else {
      LOGGER.debug("No category found with id " + id);
      return Response.status(NOT_FOUND).build();
    }
  }

  @Operation(summary = "Updates an existing category")
  @APIResponse(responseCode = "200", description = "The updated category", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleCategoria.class)))
  @Counted(name = "countUpdateCategory", description = "Counts how many times the updateCategory method has been invoked")
  @Timed(name = "timeUpdateBook", description = "Times how long it takes to invoke the updateCategory method", unit = MetricUnits.MILLISECONDS)
  @PUT
  @Path("/{id}")
  public Response updateCategory(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TransferibleCategoriaCrear.class))) @PathParam("id") Long id, @Valid TransferibleCategoriaCrear dto) {
    TransferibleCategoria categoriaActualizada = service.updateCategoria(id, dto);
    LOGGER.debug("Caategory updated with new valued " + categoriaActualizada);
    return Response.ok(categoriaActualizada).build();
  }

  @Operation(summary = "Deletes an existing category")
  @APIResponse(responseCode = "204", description = "The category has been successfully deleted")
  @Counted(name = "countDeleteCategory", description = "Counts how many times the deleteCategory method has been invoked")
  @Timed(name = "timeDeleteCategory", description = "Times how long it takes to invoke the deleteCategory method", unit = MetricUnits.MILLISECONDS)
  @DELETE
  @Path("/{id}")
  public Response deleteCategory(@Parameter(description = "Category identifier", required = true) @PathParam("id") Long id) {
    service.deleteCategoria(id);
    LOGGER.debug("Category deleted with " + id);
    return Response.noContent().build();
  }
}
