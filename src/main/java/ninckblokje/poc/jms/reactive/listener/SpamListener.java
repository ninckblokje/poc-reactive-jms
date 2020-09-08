package ninckblokje.poc.jms.reactive.listener;

import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.jms.IncomingJmsMessageMetadata;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SpamListener {

    private final Logger logger = LoggerFactory.getLogger(SpamListener.class);

    @Incoming("spam")
    @Outgoing("spamToBlock")
    public Message<String> consumeSpam(Message<String> msg) {
        logger.info("Received spam {}", getId(msg));
        return msg;
    }

    String getId(Message<String> msg) {
        return msg.getMetadata(IncomingJmsMessageMetadata.class)
                .map(IncomingJmsMessageMetadata::getMessageId)
                .orElse("UNKNOWN");
    }
}
