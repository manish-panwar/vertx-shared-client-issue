package vertx.policyupdate;

import vertx.EventBusAddress;
import vertx.httpclient.ThreadScopedHttpClient;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.http.HttpClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.net.URI;

/**
 * Created by manishk on 7/13/16.
 */
public abstract class BasePolicyUpdate {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasePolicyUpdate.class);

    @Autowired
    private Vertx vertx;
    @Autowired
    private ThreadScopedHttpClient threadScopedHttpClient;

    @PostConstruct
    public void init() {
        vertx.eventBus().consumer(getEventBusAddress().getValue()).toObservable().subscribe(message -> {
            message.reply(true);
            URI uri = URI.create("https://www.google.com:443/");
            HttpClientRequest clientRequest = threadScopedHttpClient.getConsoleHttpClient(uri).get("/");
            clientRequest.toObservable().subscribe(
                    clientResponse -> {
                        System.out.println("");
                    },
                    throwable -> {
                        System.out.println("Error");
                    },
                    () -> {
                        LOGGER.info("Google");
                    }
            );
            clientRequest.setChunked(true).end();
        });
    }

    abstract EventBusAddress getEventBusAddress();
}
