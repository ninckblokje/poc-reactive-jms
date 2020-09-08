package ninckblokje.poc.jms.reactive.config;

import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;

@ApplicationScoped
public class JmsConfig {

    @Inject
    @ConfigProperty(name = "jms.url")
    private String jmsUrl;
    @Inject
    @ConfigProperty(name = "jms.username")
    private String jmsUsername;
    @Inject
    @ConfigProperty(name = "jms.password")
    private String jmsPassword;

    @Produces
    @Named("artemisConnectionFactory")
    public ConnectionFactory artemisConnectionFactory() {
        return new ActiveMQJMSConnectionFactory(
                jmsUrl,
                jmsUsername,
                jmsPassword
        );
    }
}
