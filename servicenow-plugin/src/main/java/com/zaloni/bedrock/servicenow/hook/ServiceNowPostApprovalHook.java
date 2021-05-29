package com.zaloni.bedrock.servicenow.hook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaloni.bedrock.sdar.dto.DataAccessRequestInfo;
import com.zaloni.bedrock.sdar.dto.DataAccessRequestPostApprovalResponse;
import com.zaloni.bedrock.sdar.dto.PluginConfig;
import com.zaloni.bedrock.sdar.hook.DataAccessRequestPostApprovalHook;
import com.zaloni.bedrock.servicenow.dto.ServiceNowApprovalStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 * This is a sample ServiceNow hook that runs after the ServiceNow System Approves or Rejects a request and sends the response back to Arena.
 * This sample implementation shows how users are free to parse and process the JSON response sent from ServiceNow and do additional work if needed.
 */
public class ServiceNowPostApprovalHook implements DataAccessRequestPostApprovalHook {

    /**
     * This method is called by Arena with the Data request details and with the sample ServiceNow response that is sent from ServiceNow
     * @param dataAccessRequestInfo Details of request sent from Arena.
     * @param pluginConfig Configurations set in Arena for any Post Approval System (unused in this implementation)
     * @param postApprovalJson Response sent from the Approval System (i.e. ServiceNow in this sample implementation)
     *                         after the approval is completed.
     * @return
     */
    @Override
    public DataAccessRequestPostApprovalResponse execute(DataAccessRequestInfo dataAccessRequestInfo,
                                                         PluginConfig pluginConfig, String postApprovalJson) {
       return createDataAccessRequestResponse(postApprovalJson);
    }

    private DataAccessRequestPostApprovalResponse createDataAccessRequestResponse(String postApprovalJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        ServiceNowApprovalStatus serviceNowApprovalStatus = null;
        try {
            serviceNowApprovalStatus  = objectMapper.readValue(postApprovalJson, ServiceNowApprovalStatus.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Post approval JSON not parsable", e);
        }
        DataAccessRequestPostApprovalResponse dataAccessRequestPostApprovalResponse = new DataAccessRequestPostApprovalResponse();
        dataAccessRequestPostApprovalResponse.setStatus(WordUtils.capitalizeFully(serviceNowApprovalStatus.getStatus()));
        dataAccessRequestPostApprovalResponse.setAdditionalInfo(serviceNowApprovalStatus.getAdditionalProperties());
        return dataAccessRequestPostApprovalResponse;
    }
}
