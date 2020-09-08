package ninckblokje.poc.jms.reactive;

import io.smallrye.reactive.messaging.jms.JmsConnector;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.se.SeContainerInitializer;

@ApplicationScoped
public class PocReactiveJmsApplication {

    public static void main(String[] args) {
        SeContainerInitializer.newInstance()
                .addBeanClasses(JmsConnector.class)
                .initialize();
    }
}
