package vertx.policyupdate;

import vertx.EventBusAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by mankumar on 10/30/2014.
 */
@Component(value = "GeneralAccessPolicyUpdate")
public class GeneralAccessPolicyUpdate extends BasePolicyUpdate {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralAccessPolicyUpdate.class);

    @Override
    protected EventBusAddress getEventBusAddress() {
        return EventBusAddress.GENERAL_ACCESS_POLICY_UPDATE_PROCESS;
    }

}