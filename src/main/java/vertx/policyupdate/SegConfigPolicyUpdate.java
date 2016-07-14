package vertx.policyupdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import vertx.EventBusAddress;

/**
 * Responsible to load Account policies (aka User policies) from RESTFul service and save it to HazelCast.
 * <p>
 * Created by mankumar on 10/29/2014.
 */
@Component(value = "SegConfigPolicyUpdate")
public class SegConfigPolicyUpdate extends BasePolicyUpdate {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralAccessPolicyUpdate.class);

    @Override
    protected EventBusAddress getEventBusAddress() {
        return EventBusAddress.SEG_CONFIG_SETTINGS_UPDATE_PROCESS;
    }
}