package com.zaloni.bedrock.servicenow.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zaloni.bedrock.sdar.dto.EntityInfo;
import com.zaloni.bedrock.sdar.dto.RequestedForUser;
import com.zaloni.bedrock.servicenow.common.ServiceNowFieldsMapping;
import com.zaloni.bedrock.servicenow.common.ServiceNowInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataAccessRequest {

    private final String arenaSdarRequestid;
    private final String arenaEntities;
    private final String additionalDetails;
    private final String requestedReason;
    private final String requestedFor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public static class DataAccessRequestBuilder {
        private ServiceNowInfo servicenowInfo;
        private String arenaSdarRequestid;
        private List<? extends EntityInfo> entityInfos;
        private String requestJson;
        private String requestedReason;
        private List<RequestedForUser> requestedForUsers;
        private Map<String, Object> additionalProperties = new HashMap<>();

        public ServiceNowInfo getServicenowInfo() {
            return servicenowInfo;
        }

        public DataAccessRequestBuilder setServicenowInfo(ServiceNowInfo servicenowInfo) {
            this.servicenowInfo = servicenowInfo;
            return this;
        }

        public String getArenaSdarRequestid() {
            return arenaSdarRequestid;
        }

        public DataAccessRequestBuilder setArenaSdarRequestid(String arenaSdarRequestid) {
            this.arenaSdarRequestid = arenaSdarRequestid;
            return this;
        }

        public String getRequestJson() {
            return requestJson;
        }

        public DataAccessRequestBuilder setRequestJson(String requestJson) {
            this.requestJson = requestJson;
            return this;
        }

        public String getRequestedReason() {
            return requestedReason;
        }

        public DataAccessRequestBuilder setRequestedReason(String requestedReason) {
            this.requestedReason = requestedReason;
            return this;
        }

        public List<? extends EntityInfo> getEntityInfos() {
            return entityInfos;
        }

        public DataAccessRequestBuilder setEntityInfos(List<? extends EntityInfo> entityInfos) {
            this.entityInfos = entityInfos;
            return this;
        }

        public List<RequestedForUser> getRequestedForUsers() {
            return requestedForUsers;
        }

        public DataAccessRequestBuilder setRequestedForUsers(List<RequestedForUser> requestedForUsers) {
            this.requestedForUsers = requestedForUsers;
            return this;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public DataAccessRequestBuilder setAdditionalProperties(Map<String, Object> additionalProperties) {
            this.additionalProperties = additionalProperties;
            return this;
        }

        public String buildJsonPayload() {
            DataAccessRequest dataAccessRequest = new DataAccessRequest(this);
            return dataAccessRequest.getJson(this.servicenowInfo);
        }

        private String getAllEntityIds(List<? extends EntityInfo> entityInfos) {
            StringJoiner entities = new StringJoiner(", ");
            for (EntityInfo entityInfo : entityInfos) {
                entities.add(entityInfo.getEntityBusinessName() + "(" + entityInfo.getEntityId() + ")");
            }
            return entities.toString();
        }

        private String getRequestedFor(List<RequestedForUser> requestedForUsers) {
            StringBuilder requestForBuilder = new StringBuilder();
            boolean flag = false;

            for (RequestedForUser requestedForUser : requestedForUsers) {
                if (flag) {
                    requestForBuilder.append(", ");
                }
                flag = true;

                if (StringUtils.isNotBlank(requestedForUser.getFirstName())) {
                    requestForBuilder.append(requestedForUser.getFirstName()).append(" ");
                }
                if (StringUtils.isNotBlank(requestedForUser.getLastName())) {
                    requestForBuilder.append(requestedForUser.getLastName());
                }
                if (StringUtils.isBlank(requestedForUser.getFirstName()) && StringUtils.isBlank(requestedForUser.getLastName())) {
                    requestForBuilder.append("Name Unavailable");
                }

                requestForBuilder.append(" (").append(requestedForUser.getEmailAddress()).append(")");
            }
            return requestForBuilder.toString();
        }

        private String getAdditionalDetailsJsonString(List<? extends EntityInfo> entityInfos) {
            List<? extends EntityInfo> entityInfosTemp = new ArrayList<>(entityInfos);
            //we don't need to send fields info to approval system, since this might bloat the system if there are too many fields
            entityInfosTemp.forEach(entityInfo->{
                entityInfo.setEntityFields(null);
            });
            String jsonRequestedJson = null;
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            try {
                jsonRequestedJson  = objectMapper.writeValueAsString(entityInfosTemp);
            } catch (JsonProcessingException e) {
                //This will ideally never run. In case this does runs, we will return null;
            }
            return jsonRequestedJson;
        }
    }

    private DataAccessRequest(DataAccessRequestBuilder builder) {
        this.arenaEntities = builder.entityInfos!=null?builder.getAllEntityIds(builder.entityInfos):null;
        this.arenaSdarRequestid = builder.arenaSdarRequestid;
        this.requestedFor = builder.requestedForUsers!=null?builder.getRequestedFor(builder.requestedForUsers):null;
        this.requestedReason = builder.requestedReason;
        this.additionalDetails = builder.getAdditionalDetailsJsonString(builder.entityInfos);
    }

    private String getJson(ServiceNowInfo servicenowInfo) {
        ServiceNowFieldsMapping serviceNowFieldsMapping = servicenowInfo.getFieldsMapping();
        ObjectNode json = new ObjectMapper().createObjectNode();
        if (this.getArenaSdarRequestid() != null) {
            json.put(serviceNowFieldsMapping.getArenaAccessRequestId(), this.getArenaSdarRequestid());
        }
        if(this.getArenaEntities()!=null){
            json.put(serviceNowFieldsMapping.getEntityDetails(), this.getArenaEntities());
        }
        if(this.getRequestedFor()!=null){
            json.put(serviceNowFieldsMapping.getRequestedFor(), this.getRequestedFor());
        }
        if(this.getRequestedReason()!=null){
            json.put(serviceNowFieldsMapping.getRequestedReason(), this.getRequestedReason());
        }
        if(this.getAdditionalDetails()!=null){
            json.put(serviceNowFieldsMapping.getArenaAdditionalDetails(),this.getAdditionalDetails());
        }
        return json.toString();
    }

    public String getArenaSdarRequestid() {
        return arenaSdarRequestid;
    }

    public String getArenaEntities() {
        return arenaEntities;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public String getRequestedReason() {
        return requestedReason;
    }

    public String getRequestedFor() {
        return requestedFor;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
