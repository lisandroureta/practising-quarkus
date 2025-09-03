package org.agoncal.fascicle.quarkus.book;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
class BookApplicationLifeCycle {

  private static final Logger LOGGER = Logger.getLogger(BookApplicationLifeCycle.class);

  // Inyectamos el perfil activo directamente desde la configuración de Quarkus
  @ConfigProperty(name = "quarkus.profile")
  String activeProfile;

  void onStart(@Observes StartupEvent ev) {
    LOGGER.info("▗▄▄▖  ▄▄▄   ▄▄▄  █  ▄ ");
    LOGGER.info("▐▌ ▐▌█   █ █   █ █▄▀  ");
    LOGGER.info("▐▛▀▚▖▀▄▄▄▀ ▀▄▄▄▀ █ ▀▄ ");
    LOGGER.info("▐▙▄▞▘            █  █ ");
    LOGGER.info("                         Powered by Quarkus");
    LOGGER.info("The application Book is starting with profile " + activeProfile);
  }

  void onStop(@Observes ShutdownEvent ev) {
    LOGGER.info("The application Book is stopping...");
  }
}
