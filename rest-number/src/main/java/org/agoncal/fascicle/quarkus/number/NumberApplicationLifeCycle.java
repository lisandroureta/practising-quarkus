package org.agoncal.fascicle.quarkus.number;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class NumberApplicationLifeCycle {

  private static final Logger LOGGER = Logger.getLogger(NumberApplicationLifeCycle.class);

  void onStart(@Observes StartupEvent ev) {
    LOGGER.info("▗▖  ▗▖█  ▐▌▄▄▄▄  ▗▖   ▗▞▀▚▖ ▄▄▄ ");
    LOGGER.info("▐▛▚▖▐▌▀▄▄▞▘█ █ █ ▐▌   ▐▛▀▀▘█    ");
    LOGGER.info("▐▌ ▝▜▌     █   █ ▐▛▀▚▖▝▚▄▄▖█    ");
    LOGGER.info("▐▌  ▐▌           ▐▙▄▞▘          ");
    LOGGER.info("              Powered by Quarkus");
      LOGGER.info("The application Number is starting with profile " + ProfileManager
        .getActiveProfile());
  }

  void onStop(@Observes ShutdownEvent ev) {
    LOGGER.info("The application Number is stopping...");
  }
}
