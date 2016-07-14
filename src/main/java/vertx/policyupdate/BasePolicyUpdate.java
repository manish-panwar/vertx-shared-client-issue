package vertx.policyupdate;

import io.vertx.rxjava.core.eventbus.EventBus;
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
    private EventBus eventBus;
    @Autowired
    private ThreadScopedHttpClient threadScopedHttpClient;

    @PostConstruct
    public void init() {
        eventBus.consumer(getEventBusAddress().getValue()).toObservable().subscribe(message -> {
            message.reply(true);
            URI uri = URI.create("https://dev67.airwatchdev.com/api/mem/activesync/memconfigdevicepolicies?startIndex=1&pageSize=10&transId=81939769-8cf1-48da-bb1c-1d659a960e74&invalidateCache=true&memConfigId=48");
            HttpClientRequest clientRequest = threadScopedHttpClient.getConsoleHttpClient(uri).get("/api/mem/activesync/memconfigdevicepolicies?startIndex=1&pageSize=10&transId=81939769-8cf1-48da-bb1c-1d659a960e74&invalidateCache=true&memConfigId=48");
            clientRequest.headers().add("Authorization", "CMSURL`1 MIAGCSqGSIb3DQEHAqCAMIACAQExCzAJBgUrDgMCGgUAMIAGCSqGSIb3DQEHAQAAoIAwggKkMIICDaADAgECAgECMA0GCSqGSIb3DQEBDQUAMB0xGzAZBgNVBAMTEkFXIEFkbWluIFVzZXIgUm9vdDAeFw0xNTA4MjMxODIyMjBaFw0zNTA4MTgxODIyMjBaMBcxFTATBgNVBAMTDDY5MjpzZWdhZG1pbjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAM37NAsIvwX5McIkF9a9HXJ9PoJ0eEP88aKQwvwlICx8y84lz0aWgSIy4xclWoSfJro9nHMA+mh/gKCHB/GWGzsLcZGZbOIXA4mXs25xn6VzAZMQHtpgfrc9JIWmWTArOuxW6Lz/NDhg7Qj0HVRD6PLoBWzuTeZpr8b4k0cVN9TIYMrM9rE1P/5SJmOgEKwcShfAk4QuzG3BNSjm8+E65AcBhNzgBRcPaLEPhs9G/sXLFJjyhtck0PbAuT17yLc9P+tKASc2t7JDg7rgvYs3qCvNpGAdHFQrAaORT00ZtJl2jNWYuIACoKrZ7zhNoYtPgrFKSuuu4drEb6BbPJa26osCAwEAAaN2MHQwDgYDVR0PAQH/BAQDAgWgMBMGA1UdJQQMMAoGCCsGAQUFBwMCME0GA1UdIwRGMESAFM4farHjTO/hL5xxbIwnlmRN2WDRoRqkGDAWMRQwEgYDVQQDEwtBVyBBUEkgUm9vdIIQ82SzeNDQl5VIBPZAK0EMCTANBgkqhkiG9w0BAQ0FAAOBgQAe1n477RNXBfVKInaRf7wlInIxM1K89fOtTw86sAit3xGh/JFhVuLdJuIints1uepeWdK2URCa86PYKKCBOA9y24b4BqmEE/Xhp0j7Bgn2L4QxolbbeokcvVCrH4XVwwHuIYTt+DTsUQB2mKhK6CoQ9k779sEVL0z+ASc4B89myAAAMYIBqDCCAaQCAQEwIjAdMRswGQYDVQQDExJBVyBBZG1pbiBVc2VyIFJvb3QCAQIwCQYFKw4DAhoFAKBdMBgGCSqGSIb3DQEJAzELBgkqhkiG9w0BBwEwHAYJKoZIhvcNAQkFMQ8XDTE1MTIyMzIxMDkzOVowIwYJKoZIhvcNAQkEMRYEFMCBreSzOLzNmP6kmFGX/vQxr64ZMA0GCSqGSIb3DQEBAQUABIIBAKOiDPGk7tvL7Pa/M5ymfxZwxgcXfo/QZizTluZy6o/m9isp+OEIszFDaobrPk+ho2HPtAdKY7TgVzvsHxWZ+llTcCfvIlZlKp8FbyAn6mZXLPztODoF4ELXGiY84bqDt06h+QeZtgVilXS8tt1HEZpEFUKdK7gxc9Nyt7sWRfeRT+u+uRwSdaX0NwxXU2/7FNmbE7Vz7J7fiXPHmpi1CkpR8GpBC3xQB1SlyjSFyZ+6CHzB7Ai2kReFu8ZUcBDNB+uJLNXly3SE+63skp63MPjHtyU/PsC0MsiwWHtrBqF4p0Kw48ZfJUyM6i2iVvQ/N6ujNLdTaLi+A+gN3cpWvPgAAAAAAAA=");
            clientRequest.headers().add("Content-Type", "application/json");
            clientRequest.headers().add("aw-tenant-code", "b9K3fg+ZoRpcAT90kMXOkqVFQ/CkMVypE8pXEaw0gGM=");
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
