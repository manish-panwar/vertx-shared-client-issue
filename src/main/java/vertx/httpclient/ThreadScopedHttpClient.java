package vertx.httpclient;

import io.vertx.core.http.HttpClientOptions;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.http.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manishk on 7/12/16.
 */
@Component
public class ThreadScopedHttpClient {

    private final ThreadLocal<Map<String, HttpClient>> threadScope =
            new NamedThreadLocal("SegThreadScopedHttpClients") {
                @Override
                protected Map<String, HttpClient> initialValue() {
                    return new HashMap<>();
                }
            };

    @Autowired
    private Vertx vertx;

    public HttpClient getConsoleHttpClient(final URI uri) {
        Map<String, HttpClient> scope = this.threadScope.get();
        HttpClient client = scope.get(uri.getHost());
        if (client == null) {
            client = createClient(uri, 50, 3000, true);
            scope.put(uri.getHost(), client);
        }
        return client;
    }

    private HttpClient createClient(final URI uri, final int poolSize, int connectionTimeout, final boolean ignoreSslErrors) {
        boolean secure = uri.toString().startsWith("https:");
        HttpClientOptions options = new HttpClientOptions()
                .setDefaultHost(uri.getHost())
                .setSsl(secure)
                .setConnectTimeout(connectionTimeout)
                .setMaxPoolSize(poolSize)
                .setTryUseCompression(true);
        // If port isn't set then Vertx uses default port as 80 - which will cause issues.
        if (uri.getPort() == -1) {
            options.setDefaultPort(secure ? 443 : 80);
        } else {
            options.setDefaultPort(uri.getPort());
        }
        if (ignoreSslErrors) {
            options.setTrustAll(true);
            options.setVerifyHost(false);
        }
        return vertx.createHttpClient(options);
    }
}