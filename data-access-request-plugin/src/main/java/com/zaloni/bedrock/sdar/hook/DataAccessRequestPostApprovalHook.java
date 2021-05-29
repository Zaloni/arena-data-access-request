package com.zaloni.bedrock.sdar.hook;

import com.zaloni.bedrock.sdar.dto.DataAccessRequestInfo;
import com.zaloni.bedrock.sdar.dto.DataAccessRequestPostApprovalResponse;
import com.zaloni.bedrock.sdar.dto.PluginConfig;

/**
 * This is a hook interface that can runs after the Approval System Approves or Rejects a request and sends the response back to Arena.
 * Users are free to send any JSON response and the responsibility of handling the response lies with the implementation that the user writes.
 * see ServiceNowPostApprovalHook for a sample ServiceNow implementation
 */
public interface DataAccessRequestPostApprovalHook {

    /**
     * This method is called by Arena with the dataAccessRequestInfo and postApprovalJson.
     * See sample ServiceNow implementation ServiceNowPostApprovalHook for more information
     *
     * @param dataAccessRequestInfo Request information from Arena
     * @param pluginConfig Configurations set in Arena for any Post Approval System (if any)
     * @param postApprovalJson Response sent to Arena from the Approval System after the approval is done
     * @return
     */
    DataAccessRequestPostApprovalResponse execute(DataAccessRequestInfo dataAccessRequestInfo, PluginConfig pluginConfig, String postApprovalJson);
}
