package ninckblokje.poc.jms.reactive.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class SpamHandlerService {

    @Inject
    @ConfigProperty(name = "spam.ack", defaultValue = "true")
    private Boolean ackMsg;

    private final Logger logger = LoggerFactory.getLogger(SpamHandlerService.class);

    @Incoming("spamToHandle")
    public CompletionStage<Void> handle(Message<String> msg) {
        logger.info("Received spam message {}, acking: {}", msg.getPayload(), ackMsg);
        return (ackMsg) ? msg.ack() : msg.nack(new RuntimeException("Acking is disabled"));
    }
}
