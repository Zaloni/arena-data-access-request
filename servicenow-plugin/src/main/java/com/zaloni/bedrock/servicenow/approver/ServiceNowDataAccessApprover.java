package com.zaloni.bedrock.servicenow.approver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaloni.bedrock.sdar.approver.DataAccessRequestApprover;
import com.zaloni.bedrock.sdar.dto.DataAccessRequestInfo;
import com.zaloni.bedrock.sdar.dto.DataAccessRequestResponse;
import com.zaloni.bedrock.sdar.dto.PluginConfig;
import com.zaloni.bedrock.sdar.enums.DataAccessRequestStatus;
import com.zaloni.bedrock.servicenow.common.ServiceNowFieldsMapping;
import com.zaloni.bedrock.servicenow.common.ServiceNowInfo;
import com.zaloni.bedrock.servicenow.connector.Connector;
import com.zaloni.bedrock.servicenow.dto.DataAccessRequest;
import com.zaloni.bedrock.servicenow.dto.ServiceNowResponse;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * This is sample ServiceNow Approver plugin implementation that implements DataAccessRequestApprover.
 * Users an refer this implementation to create their Custom Approver Plugin.
 */
public class ServiceNowDataAccessApprover implements DataAccessRequestApprover {

    public static final Logger LOGGER = LoggerFactory.getLogger(ServiceNowDataAccessApprover.class);


    /**
     * Submits a approval request to ServiceNow
     *
     * @param dataAccessRequestInfo Details of request sent from Arena
     * @param pluginConfig Configurations set in Arena for ServiceNow
     * @return
     */
    @Override
    public DataAccessRequestResponse processApproval(DataAccessRequestInfo dataAccessRequestInfo,
                                                     PluginConfig pluginConfig) {

        ServiceNowInfo servicenowInfo = getServiceNowInfo(pluginConfig);

        String serviceNowRequestPayload = new DataAccessRequest.DataAccessRequestBuilder()
                .setServicenowInfo(servicenowInfo)
                .setArenaSdarRequestid(dataAccessRequestInfo.getRequestId())
                .setEntityInfos(dataAccessRequestInfo.getEntityInfos())
                .setRequestedForUsers(dataAccessRequestInfo.getRequestedFor())
                .setRequestedReason(dataAccessRequestInfo.getExplanationForRequest())
                .buildJsonPayload();

        return submitServiceNowApprovalRequest(servicenowInfo, serviceNowRequestPayload);
    }

    /**
     * Submits an approval request to ServiceNow.
     * Default implementation uses Rest API to submit approval requests.
     * But (if required) this method can be overridden to customize the connection to ServiceNow.
     *
     * @param servicenowInfo
     * @param serviceNowRequestPayload
     * @return
     */
    protected DataAccessRequestResponse submitServiceNowApprovalRequest(ServiceNowInfo servicenowInfo,
                                                                        String serviceNowRequestPayload) {
        String url = null;
        String jsonResponse = null;
        int responseStatusCode = -1;
        try {
            url = servicenowInfo.getUrl()+"/api/" + servicenowInfo.getNamespace() + "/table/" + servicenowInfo.getTable();
            String usernamePassword = servicenowInfo.getUsername() + ":" +servicenowInfo.getPassword();
            String encodedCredentials = new BASE64Encoder().encode(usernamePassword.getBytes(StandardCharsets.UTF_8));
            ObjectMapper mapper = new ObjectMapper();
            LOGGER.info("Submitting approval request to [ServiceNow] - url: [{}], with request payload: [{}]", url, serviceNowRequestPayload);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials);
            httpPost.setEntity(new StringEntity(serviceNowRequestPayload));
            Pair<Integer, String> responsePair = new Connector().post(httpPost);
            responseStatusCode = responsePair.getLeft();
            jsonResponse = responsePair.getRight();
            if (responseStatusCode >= 200 && responseStatusCode < 300) {
                ServiceNowResponse serviceNowResponse = mapper.readValue(jsonResponse, ServiceNowResponse.class);
                DataAccessRequestResponse dataAccessRequestResponse = new DataAccessRequestResponse()
                        .setStatus(DataAccessRequestStatus.PENDING_FULFILLMENT.getStatusValue())
                        .setIntegrationId(serviceNowResponse.getResult()!=null?serviceNowResponse.getResult().getSysId():null)
                        .setStatusMessage("Approval request submitted successfully to [ServiceNow] - response code: ["
                        + responseStatusCode + "], response: ["+ jsonResponse+ "], request: ["+
                                serviceNowRequestPayload + "], url: [" + url + "]");
                return dataAccessRequestResponse;
            } else {
                throw new IllegalStateException("Could not submit the approval request to [ServiceNow] - response code: ["
                        + responseStatusCode + "], response: ["+ jsonResponse+ "], " +
                        "request: ["+ serviceNowRequestPayload + "], url: [" + url + "]");
            }
        } catch (Exception e){
            throw new IllegalStateException("Could not submit the approval request to [ServiceNow] - response: ["+ jsonResponse+ "], " +
                    "request: ["+ serviceNowRequestPayload + "], url: [" + url + "]", e);
        }

    }

    /**
     * Retrieves the ServiceNow Related configuration information
     * e.g. ServiceNow related connection information like username, password and table url
     *
     * @param pluginConfig
     * @return
     */
    public ServiceNowInfo getServiceNowInfo(PluginConfig pluginConfig) {
        Map<String, Object> serviceNowConfigs = (Map<String, Object>) pluginConfig.getConfigs().get("servicenow");
        String username = (String) serviceNowConfigs.get("username");
        String password = (String) serviceNowConfigs.get("password");
        String url = (String) serviceNowConfigs.get("base_url");
        String namespace = (String) serviceNowConfigs.get("namespace");
        String table = (String) serviceNowConfigs.get("table_name");
        Map<String, Object> fieldsMapping = (Map<String, Object>) serviceNowConfigs.get("fieldsMapping");
        ServiceNowInfo serviceNowInfo = new ServiceNowInfo();
        serviceNowInfo.setUsername(username);
        serviceNowInfo.setPassword(password);
        serviceNowInfo.setUrl(url);
        serviceNowInfo.setNamespace(namespace);
        serviceNowInfo.setTable(table);
        ServiceNowFieldsMapping serviceNowFieldsMapping = new ServiceNowFieldsMapping();
        serviceNowFieldsMapping.setEntityDetails((String) fieldsMapping.get("arenaEntities"));
        serviceNowFieldsMapping.setArenaAccessRequestId((String) fieldsMapping.get("arenaSdarRequestid"));
        serviceNowFieldsMapping.setRequestedFor((String) fieldsMapping.get("requestedFor"));
        serviceNowFieldsMapping.setRequestedReason((String) fieldsMapping.get("requestedReason"));
        serviceNowFieldsMapping.setArenaAdditionalDetails((String) fieldsMapping.get("arenaAdditionalDetails"));
        serviceNowFieldsMapping.setRequestedFromProjectId((String) fieldsMapping.get("requestedFromProjectId"));
        serviceNowInfo.setFieldsMapping(serviceNowFieldsMapping);
        return serviceNowInfo;

    }
}
