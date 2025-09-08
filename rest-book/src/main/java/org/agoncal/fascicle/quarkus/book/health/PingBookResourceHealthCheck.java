package org.agoncal.fascicle.quarkus.book.health;

import org.agoncal.fascicle.quarkus.book.recurso.RecursoLibro;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Liveness
@ApplicationScoped
public class PingBookResourceHealthCheck implements HealthCheck {

  @Inject
  RecursoLibro recursoLibro;

  @Override
  public HealthCheckResponse call() {
    recursoLibro.ping();
    return HealthCheckResponse.named("Ping Book REST Endpoint").up().build();
  }
}
