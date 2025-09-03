package org.agoncal.fascicle.quarkus.number;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class NumberApplicationLifeCycle {

  private static final Logger LOGGER = Logger.getLogger(NumberApplicationLifeCycle.class);

  // Inyectamos el perfil activo
  @ConfigProperty(name = "quarkus.profile")
  String activeProfile;

  void onStart(@Observes StartupEvent ev) {
    LOGGER.info("▗▖  ▗▖█  ▐▌▄▄▄▄  ▗▖   ▗▞▀▚▖ ▄▄▄ ");
    LOGGER.info("▐▛▚▖▐▌▀▄▄▞▘█ █ █ ▐▌   ▐▛▀▀▘█    ");
    LOGGER.info("▐▌ ▝▜▌     █   █ ▐▛▀▚▖▝▚▄▄▖█    ");
    LOGGER.info("▐▌  ▐▌           ▐▙▄▞▘          ");
    LOGGER.info("              Powered by Quarkus");
    LOGGER.info("The application Number is starting with profile " + activeProfile);
  }

  void onStop(@Observes ShutdownEvent ev) {
    LOGGER.info("The application Number is stopping...");
  }
}
