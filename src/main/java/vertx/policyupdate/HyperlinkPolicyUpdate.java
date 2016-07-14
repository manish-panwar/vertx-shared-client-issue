package vertx.policyupdate;

import vertx.EventBusAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by manishk on 10/9/2015.
 */
@Component(value = "HyperlinkPolicyUpdate")
public class HyperlinkPolicyUpdate extends BasePolicyUpdate {


    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralAccessPolicyUpdate.class);

    @Override
    protected EventBusAddress getEventBusAddress() {
        return EventBusAddress.HYPERLINK_POLICY_UPDATE_PROCESS;
    }
}