package com.zaloni.bedrock.sdar.approver;

import com.zaloni.bedrock.sdar.dto.DataAccessRequestInfo;
import com.zaloni.bedrock.sdar.dto.DataAccessRequestResponse;
import com.zaloni.bedrock.sdar.dto.PluginConfig;

import java.util.Map;

/**
 *
 */
public interface DataAccessRequestApprover {

    /**
     * Creates an approval request to an Approval System.
     * See sample ServiceNow implementation ServiceNowDataAccessApprover for more information.
     *
     * @param dataAccessRequestInfo details of request sent from Arena to the plugin
     * @param pluginConfig configurations set in Arena for Approval System integration
     * @return
     */
    DataAccessRequestResponse processApproval(DataAccessRequestInfo dataAccessRequestInfo, PluginConfig pluginConfig);
}
