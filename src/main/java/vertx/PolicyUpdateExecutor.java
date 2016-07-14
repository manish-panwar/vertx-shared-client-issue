package vertx;

import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.rxjava.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

import static vertx.EventBusAddress.*;

/**
 * Created by manishk on 7/13/16.
 */
@Component
public class PolicyUpdateExecutor {

    private static final List<String> bulkUpdateExecutors = Arrays.asList(DEVICE_POLICY_BULK_UPDATE_PROCESS.getValue(),
            ACCOUNT_POLICY_BULK_UPDATE_PROCESS.getValue(),
            EAS_DEVICE_POLICY_BULK_UPDATE_PROCESS.getValue(), GENERAL_ACCESS_POLICY_UPDATE_PROCESS.getValue(),
            HYPERLINK_POLICY_UPDATE_PROCESS.getValue(), MAIL_CLIENT_POLICY_BULK_UPDATE_PROCESS.getValue(),
            MANAGED_ATTACHMENT_POLICY_UPDATE_PROCESS.getValue(), UNMANAGED_ATTACHMENT_POLICY_UPDATE_PROCESS.getValue(),
            SYNC_FILTERS_POLICY_UPDATE_PROCESS.getValue(), ENCRYPTION_KEY_DATA_PAYLOAD_BULK_UPDATE_PROCESS.getValue(),
            ENCRYPTION_KEY_DATA_PAYLOAD_DELTA_REMOVE_PROCESS.getValue(), ENCRYPTION_KEY_DATA_PAYLOAD_DELTA_UPDATE_PROCESS.getValue());

    @Autowired
    private Vertx vertx;

    @PostConstruct
    public void init() {
        vertx.setPeriodic(3000, doNothing -> {
           executeUpdate();
        });
    }

    public void executeUpdate() {
        bulkUpdateExecutors.forEach(verticalAddress -> {
            vertx.eventBus().sendObservable(verticalAddress, null, new DeliveryOptions().setSendTimeout(3000))
                    .subscribe(reply -> {
                    });
        });
    }
}
