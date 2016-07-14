package vertx.policyupdate;

import vertx.EventBusAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Responsible to load EAS-Device Type policies from RESTFul service and save it to HazelCast.
 * <p>
 * It will load the EAS-Device Type policies in chunks, keep it in memory, and when all the policies are loaded from server, swap them all in HazelCast with old one in one go.
 * <p>
 * <p>
 * Created by mankumar on 1/30/2015.
 */
@Component(value = "EasDeviceTypePolicyUpdate")
public class EasDeviceTypePolicyUpdate extends BasePolicyUpdate {


    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralAccessPolicyUpdate.class);

    @Override
    protected EventBusAddress getEventBusAddress() {
        return EventBusAddress.EAS_DEVICE_POLICY_BULK_UPDATE_PROCESS;
    }
}
