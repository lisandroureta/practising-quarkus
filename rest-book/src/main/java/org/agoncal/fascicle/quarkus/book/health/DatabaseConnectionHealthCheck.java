package org.agoncal.fascicle.quarkus.book.health;

import org.agoncal.fascicle.quarkus.book.modelo.Book;
import org.agoncal.fascicle.quarkus.book.servicio.ServicioLibro;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibro;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@Readiness
@ApplicationScoped
public class DatabaseConnectionHealthCheck implements HealthCheck {

  @Inject
  ServicioLibro servicioLibro;

  @Override
  public HealthCheckResponse call() {
    HealthCheckResponseBuilder responseBuilder = HealthCheckResponse
      .named("Book Datasource connection health check");

    try {
      List<TransferibleLibro> books = servicioLibro.findAllBooks();
      responseBuilder.withData("Number of books in the database", books.size()).up();
    } catch (IllegalStateException e) {
      responseBuilder.down();
    }

    return responseBuilder.build();
  }
}
