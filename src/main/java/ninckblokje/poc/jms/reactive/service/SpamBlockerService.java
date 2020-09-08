package ninckblokje.poc.jms.reactive.service;

import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SpamBlockerService {

    private final Logger logger = LoggerFactory.getLogger(SpamBlockerService.class);

    @Inject
    @ConfigProperty(name = "spam.sleep", defaultValue = "5000")
    private Long sleepTime;

    @Incoming("spamToBlock")
    @Outgoing("spamToHandle")
    @Blocking("blocking-pool")
    public Message<String> block(Message<String> msg) throws InterruptedException {
        logger.info("Blocking spam message, sleeping for {} ms", sleepTime);
        Thread.sleep(sleepTime);
        return msg;
    }
}
