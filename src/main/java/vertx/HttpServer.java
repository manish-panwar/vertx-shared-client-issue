package vertx;

import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.web.Router;
import io.vertxbeans.rxjava.ContextRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Created by manishk on 7/13/16.
 */
@Component
public class HttpServer {


    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    @Autowired
    private Vertx vertx;
    @Autowired
    private VertxOptions vertxOptions;
    @Autowired
    private ContextRunner contextRunner;
    @Autowired
    private PolicyUpdateExecutor policyUpdateExecutor;

    @PostConstruct
    public void init() {
        LOGGER.info("Creating {} instances of HttpServer", vertxOptions.getEventLoopPoolSize());
        try {
            contextRunner.executeBlocking(1,
                    () -> createHttpServer().buffer(2), 1, MINUTES);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            LOGGER.error("Error while starting HTTP server.", e);
        }
    }

    private Observable<io.vertx.rxjava.core.http.HttpServer> createHttpServer() {
        Router rootRouter = Router.router(vertx);
        rootRouter.get("/").handler(context -> {
            LOGGER.info("Listening on port {}", 8090);
            policyUpdateExecutor.executeUpdate();
            context.response().end();
        });
        HttpServerOptions options = new HttpServerOptions().setPort(8090).setCompressionSupported(true);

        return vertx.createHttpServer(options).requestHandler(rootRouter::accept).listenObservable()
                .doOnCompleted(() -> {
                    LOGGER.info("Listening on port {}", 8090);
                });
    }
}
