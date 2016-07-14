package vertx;

/**
 * Created by ssaha on 10/10/2014.
 */
public enum EventBusAddress {

    DEVICE_POLICY_BULK_UPDATE_PROCESS("device.policy.bulk.update.process"),
    DEVICE_POLICY_DELTA_UPDATE_PROCESS("device.policy.delta.update.process"),
    DEVICE_POLICY_DELTA_REMOVE_PROCESS("device.policy.delta.remove.process"),
    ACCOUNT_POLICY_BULK_UPDATE_PROCESS("account.policy.bulk.update.process"),
    MAIL_CLIENT_POLICY_BULK_UPDATE_PROCESS("mail.client.policy.bulk.update.process"),
    EAS_DEVICE_POLICY_BULK_UPDATE_PROCESS("eas.device.policy.bulk.update.process"),
    GENERAL_ACCESS_POLICY_UPDATE_PROCESS("general.access.policy.update.process"),
    SYNC_FILTERS_POLICY_UPDATE_PROCESS("sync.filter.policy.rest.endpoint"),
    SEG_CONFIG_SETTINGS_UPDATE_PROCESS("seg.config.settings.update.process"),
    HYPERLINK_POLICY_UPDATE_PROCESS("hyperlink.policy.update.process"),

    MANAGED_ATTACHMENT_POLICY_UPDATE_PROCESS("managed.attachment.policy.update.process"),
    UNMANAGED_ATTACHMENT_POLICY_UPDATE_PROCESS("unmanaged.attachment.policy.update.process"),

    IMPERSONATION_CREDENTIAL_POLICY_BULK_UPDATE_PROCESS("impersonation.credential.policy.bulk.update.process"),
    ENCRYPTION_KEY_DATA_PAYLOAD_BULK_UPDATE_PROCESS("encryption.key.data.payload.bulk.update.process"),
    ENCRYPTION_KEY_DATA_PAYLOAD_DELTA_UPDATE_PROCESS("encryption.key.data.payload.delta.update.process"),
    ENCRYPTION_KEY_DATA_PAYLOAD_DELTA_REMOVE_PROCESS("encryption.key.data.payload.delta.remove.process"),
    EMAIL_SECURITY_TAG_POLICY_UPDATE_PROCESS("email.security.tag.actions.policy.update.process"),

    CHECK_COMPLIANCE("check.compliance"),
    REFRESH_BULK_POLICIES("refresh.bulk.policies"),
    REFRESH_DELTA_POLICIES("refresh.delta.policies"),
    PERFORMANCE_STATS_PROCESSOR("performance_stats_processor"),
    RESCHEDULE_BULK_UPDATE_TIMER("reschedule.bulk.update.timer"),

    INVALIDATE_LOCAL_CACHE_FOR_POLICY_AND_INDEXES("invalidate.local.policy.objects"),
    INVALIDATE_LOCAL_STAT_MAP_INDEXES("invalidate.local.stat.map.indexes"),
    SYNCHRONIZE_LOCAL_CONTENT_LOGGING_OPTIONS_DATA("synchronize.local.content.logging.options.data"),
    DIAGNOSTIC_SUPPORTABILITY_DATA("diagnostic.supportability.data"),

    WRITE_SEG_CONFIG_FILE("write.seg.config.file");

    private String value;

    private EventBusAddress(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
